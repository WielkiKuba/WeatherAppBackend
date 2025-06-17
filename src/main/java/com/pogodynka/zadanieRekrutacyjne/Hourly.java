package com.pogodynka.zadanieRekrutacyjne;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Hourly {

    @JsonProperty("surface_pressure")
    private List<Double> surfacePressure;

    public void setSurfacePressure(List<Double> surfacePressure){
        this.surfacePressure = surfacePressure;
    }

    public List<Double> getSurfacePressure(){
        return surfacePressure;
    }

}
