package ru.akylyar.springcourse.FirstRestApp.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import ru.akylyar.springcourse.FirstRestApp.models.Sensor;

public class MeasurementDTO {
    @NotNull(message = "Значение темпераутуры не может быть null")
    @Range(min = -100, max = 100, message = "Сенсор отслеживает температуру от -100 до 100 по Цельсию")
    private Double value;

    @NotNull
    private Boolean raining;
    private Sensor sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
