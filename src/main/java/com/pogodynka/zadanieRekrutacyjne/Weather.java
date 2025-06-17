package com.pogodynka.zadanieRekrutacyjne;

public class Weather {

    private Long latitude;
    private Long longitude;
    private Daily daily;
    private Hourly hourly;

    public void setLatitude(Long latitude){
        this.latitude = latitude;
    }
    public void setLongitude(Long longitude){
        this.longitude = longitude;
    }
    public void setDaily(Daily daily){
        this.daily = daily;
    }
    public void setHourly(Hourly hourly){
        this.hourly = hourly;
    }

    public Long getLatitude(){
        return latitude;
    }
    public Long getLongitude(){
        return longitude;
    }
    public Daily getDaily(){
        return daily;
    }
    public Hourly getHourly(){
        return hourly;
    }
}
