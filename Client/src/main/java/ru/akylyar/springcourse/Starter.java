package ru.akylyar.springcourse;

public class Starter {
    private static final String getMeasurementsUrl = "http://localhost:8080/measurements";
    private static final String addMeasurementsUrl = "http://localhost:8080/measurements/add";
    private static final String registerSensorUrl = "http://localhost:8080/sensors/registration";

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.sendMeasurements(addMeasurementsUrl, "Weather 1.0");
    }
}
