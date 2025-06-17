package com.pogodynka.zadanieRekrutacyjne;

public class Day {

    private String date;
    private Double temperatureMax;
    private Double temperatureMin;
    private Integer weatherCode;
    private Double sunshineDuration;
    private Double estimatedEnergy;

    public String getDate(){
        return date;
    }
    public Double getTemperatureMax(){
        return temperatureMax;
    }
    public Double getTemperatureMin(){
        return temperatureMin;
    }
    public Integer getWeatherCode(){
        return weatherCode;
    }
    public Double getSunshineDuration(){
        return sunshineDuration;
    }
    public Double getEstimatedEnergy(){
        return estimatedEnergy;
    }

    public void setDate(String date){
        this.date = date;
    }
    public void setTemperatureMax(Double temperatureMax){
        this.temperatureMax = temperatureMax;
    }
    public void setTemperatureMin(Double temperatureMin){
        this.temperatureMin = temperatureMin;
    }
    public void setWeatherCode(Integer weatherCode){
        this.weatherCode = weatherCode;
    }
    public void setSunshineDuration(Double sunshineDuration){
        this.sunshineDuration = sunshineDuration;
    }
    public void setEstimatedEnergy(Double estimatedEnergy){
        this.estimatedEnergy = estimatedEnergy;
    }
}
