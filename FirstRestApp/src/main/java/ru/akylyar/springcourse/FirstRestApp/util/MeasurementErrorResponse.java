package ru.akylyar.springcourse.FirstRestApp.util;

import java.time.LocalDateTime;

public class MeasurementErrorResponse {
    private String message;
    private LocalDateTime timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
