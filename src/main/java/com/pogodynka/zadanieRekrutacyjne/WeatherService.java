package com.pogodynka.zadanieRekrutacyjne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Autowired
    public WeatherService(WebClient webClient){this.webClient = webClient;}

    public Mono<Weather> getWeather(double latitude,double longitude){
        String apiUrl = "/v1/forecast";
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiUrl)
                        .queryParam("latitude",latitude)
                        .queryParam("longitude",longitude)
                        .queryParam("hourly","surface_pressure")
                        .queryParam("daily","temperature_2m_max","temperature_2m_min","weathercode","sunshine_duration")
                        .build()
                )
                .retrieve()
                .bodyToMono(Weather.class)
                .doOnError(e -> {
                    System.err.println("Error for latitude: "+latitude+" longitude: "+longitude);
                    System.err.println("Type: " + e.getClass().getSimpleName());
                    System.err.println("Message: " + e.getMessage());
                })
                .onErrorResume(e -> {
                    if (e instanceof WebClientResponseException) {
                        WebClientResponseException ex = (WebClientResponseException) e;
                        if (ex.getStatusCode().is4xxClientError()) {
                            return Mono.error(new IllegalArgumentException(
                                    "Invalid request to external API: " + ex.getStatusText() + ". Details: " + ex.getResponseBodyAsString(), ex));
                        } else if (ex.getStatusCode().is5xxServerError()) {
                            return Mono.error(new RuntimeException(
                                    "External weather API returned a server error: " + ex.getStatusText(), ex));
                        }
                    }
                    else if (e instanceof ConnectException) {
                        return Mono.error(new IOException("No connection to the external weather API.", e));
                    }
                    else if (e instanceof IOException) {
                        return Mono.error(new IOException("I/O error during communication with the weather API.", e));
                    }
                    return Mono.error(new RuntimeException(
                            "Internal error while processing weather data.", e));
                });
    }
    public Mono<Day> getDayWeather(double latitude,double longitude){
        return getWeekWeather(latitude,longitude).map(days -> {
            if(days!=null&&!days.isEmpty()){
                return days.get(0);
            }
            else{
                return null;
            }
        });
    }
    public Mono<Summary> getSummary(double latitude, double longitude){
        Mono<List<Day>> monoListDay= getWeekWeather(latitude,longitude);
        Mono<Weather> monoListWeather = getWeather(latitude,longitude);
        return Mono.zip(monoListDay,monoListWeather)
                .map(summaryList ->{
                    Summary summary = new Summary();
                    List<Day> dayList = summaryList.getT1();
                    List<Double> pressureList = summaryList.getT2().getHourly().getSurfacePressure();
                    if(pressureList.isEmpty()||dayList.isEmpty()){
                        throw new RuntimeException("Required weather data is unavailable or incomplete");
                    }else{
                        double avgSunshineDuration = 0;
                        double avgPressure = 0;
                        int rainy = 0;
                        int notRainy = 0;
                        for(Double pressure:pressureList){
                            avgPressure+=pressure;
                        }
                        summary.setAvgPressure(avgPressure/pressureList.size());
                        double maxTemp=dayList.get(0).getTemperatureMax();
                        double minTemp=dayList.get(0).getTemperatureMin();
                        for(Day day:dayList){
                            avgSunshineDuration += day.getSunshineDuration();
                            if(day.getTemperatureMax()>maxTemp){
                                maxTemp = day.getTemperatureMax();
                            }
                            if(day.getTemperatureMin()<minTemp){
                                minTemp = day.getTemperatureMin();
                            }
                            if(day.getWeatherCode()<50){
                                notRainy++;
                            }else{
                                rainy++;
                            }
                        }
                        if(rainy>notRainy){
                            summary.setRainy(true);
                        }
                        summary.setAvgSunshineDuration(avgSunshineDuration/dayList.size());
                        summary.setMaxTemp(maxTemp);
                        summary.setMinTemp(minTemp);
                        return summary;
                    }
                });
    }

    public Mono<List<Day>> getWeekWeather(double latitude,double longitude){
        double power = 2.5;
        double efficiency = 0.2;
        return getWeather(latitude,longitude)
                .map(Weather::getDaily)
                .map(daily -> {
                    List<Day> formatedDays = new ArrayList<>();
                    if(daily !=null && daily.getTime()!=null && daily.getTemperatureMax()!=null && daily.getTemperatureMin() != null && daily.getWeatherCode()!=null){
                        int days = daily.getTime().size();
                        int listSize = daily.getTime().size();
                        if(daily.getWeatherCode().size()!=listSize||daily.getTemperatureMin().size()!=listSize||daily.getTemperatureMax().size()!=listSize||daily.getSunshineDuration().size()!=listSize){
                            throw new RuntimeException("Inconsistent list sizes in daily weather data.");
                        }else{
                            for(int i =0;i<days;i++){
                                Day day = new Day();
                                day.setSunshineDuration(daily.getSunshineDuration().get(i));
                                day.setDate(daily.getTime().get(i));
                                day.setWeatherCode(daily.getWeatherCode().get(i));
                                day.setTemperatureMin(daily.getTemperatureMin().get(i));
                                day.setTemperatureMax(daily.getTemperatureMax().get(i));
                                day.setEstimatedEnergy((daily.getSunshineDuration().get(i)/3600)*power*efficiency);
                                formatedDays.add(day);
                            }
                            return formatedDays;
                        }
                    }else{
                        return Collections.emptyList();
                    }
                });
    }

}
