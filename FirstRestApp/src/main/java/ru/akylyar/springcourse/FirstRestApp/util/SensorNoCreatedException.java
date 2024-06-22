package ru.akylyar.springcourse.FirstRestApp.util;

public class SensorNoCreatedException extends RuntimeException{
    public SensorNoCreatedException(String message) {
        super(message);
    }
}
