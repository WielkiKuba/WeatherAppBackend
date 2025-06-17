package com.pogodynka.zadanieRekrutacyjne;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Daily {

    private List<String> time;
    @JsonProperty("temperature_2m_max")
    private List<Double> temperatureMax;
    @JsonProperty("temperature_2m_min")
    private List<Double> temperatureMin;
    @JsonProperty("weathercode")
    private List<Integer> weatherCode;
    @JsonProperty("sunshine_duration")
    private List<Double> sunshineDuration;

    public void setTime(List<String> time){
        this.time = time;
    }
    public void setTemperatureMax(List<Double> temperatureMax){
        this.temperatureMax = temperatureMax;
    }
    public void setTemperatureMin(List<Double> temperatureMin){
        this.temperatureMin = temperatureMin;
    }
    public void setWeatherCode(List<Integer> weatherCode){
        this.weatherCode = weatherCode;
    }
    public void setSunshineDuration(List<Double> sunshineDuration){
        this.sunshineDuration = sunshineDuration;
    }

    public List<String> getTime(){
        return time;
    }
    public List<Double> getTemperatureMax(){
        return temperatureMax;
    }
    public List<Double> getTemperatureMin(){
        return temperatureMin;
    }
    public List<Integer> getWeatherCode(){
        return weatherCode;
    }
    public List<Double> getSunshineDuration(){
        return sunshineDuration;
    }
}
