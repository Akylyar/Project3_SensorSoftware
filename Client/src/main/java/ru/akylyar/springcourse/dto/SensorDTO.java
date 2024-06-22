package ru.akylyar.springcourse.dto;

public class SensorDTO {
    private String name;

    public SensorDTO() {}

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor {" +
                "name='" + name + '\'' +
                '}' + "\n";
    }
}
