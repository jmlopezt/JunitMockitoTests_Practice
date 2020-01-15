package Junit5;

public class TemperatureCalculator {
    private Float temperatureCelsius;

    public TemperatureCalculator(Float temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public Float getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(Float temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public float toFahrenheit(){
        return ((temperatureCelsius * 9) / 5) + 32;
    }
}
