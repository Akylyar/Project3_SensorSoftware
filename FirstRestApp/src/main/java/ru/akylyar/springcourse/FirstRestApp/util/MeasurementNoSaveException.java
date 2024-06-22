package ru.akylyar.springcourse.FirstRestApp.util;

public class MeasurementNoSaveException extends RuntimeException {
    public MeasurementNoSaveException(String message) {
        super(message);
    }
}
