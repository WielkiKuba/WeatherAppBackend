package com.pogodynka.zadanieRekrutacyjne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin("*")
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){this.weatherService=weatherService;}

    @GetMapping("/weather")
    public ResponseEntity<Mono<Weather>> getWeather(@RequestParam double latitude,@RequestParam double longitude){
        if(!(latitude>90||latitude<-90)&&!(longitude>180||longitude<-180)){
            return ResponseEntity.ok(weatherService.getWeather(latitude,longitude));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/weather/weekly")
    public ResponseEntity<Mono<List<Day>>> getWeeklyWeather(@RequestParam double latitude, @RequestParam double longitude){
        if(!(latitude>90||latitude<-90)&&!(longitude>180||longitude<-180)){
            return ResponseEntity.ok(weatherService.getWeekWeather(latitude,longitude));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/weather/daily")
    public ResponseEntity<Mono<Day>> getDailyWeather(@RequestParam double latitude, @RequestParam double longitude){
        if(!(latitude>90||latitude<-90)&&!(longitude>180||longitude<-180)){
            return ResponseEntity.ok(weatherService.getDayWeather(latitude,longitude));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/weather/summary")
    public ResponseEntity<Mono<Summary>> getSummary(@RequestParam double latitude, @RequestParam double longitude){
        if(!(latitude>90||latitude<-90)&&!(longitude>180||longitude<-180)){
            return ResponseEntity.ok(weatherService.getSummary(latitude,longitude));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
