package com.pogodynka.zadanieRekrutacyjne;

public class Summary {

    private Double avgPressure;
    private Double avgSunshineDuration;
    private Double maxTemp;
    private Double minTemp;
    private boolean rainy;

    public void setAvgPressure(Double avgPressure) {
        this.avgPressure = avgPressure;
    }
    public void setAvgSunshineDuration(Double avgSunshineDuration){
        this.avgSunshineDuration = avgSunshineDuration;
    }
    public void setRainy(boolean rainy){
        this.rainy = rainy;
    }
    public void setMaxTemp(Double maxTemp){
        this.maxTemp = maxTemp;
    }
    public void setMinTemp(Double minTemp){
        this.minTemp = minTemp;
    }

    public Double getAvgPressure(){
        return avgPressure;
    }
    public Double getMaxTemp(){
        return maxTemp;
    }
    public Double getMinTemp(){
        return minTemp;
    }
    public Double getAvgSunshineDuration(){
        return avgSunshineDuration;
    }
    public boolean getRainy(){
        return rainy;
    }
}
