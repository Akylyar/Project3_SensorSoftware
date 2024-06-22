package ru.akylyar.springcourse.FirstRestApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Имя не может быть пустое")
    @Size(min = 3, max = 30, message = "Имя сенсора может содержать от 3 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
