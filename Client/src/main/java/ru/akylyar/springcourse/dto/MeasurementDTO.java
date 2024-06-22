package ru.akylyar.springcourse.dto;

public class MeasurementDTO {
    private Double value;
    private Boolean raining;
    private SensorDTO sensorDTO;

    public MeasurementDTO() {}

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensorDTO) {
        this.value = value;
        this.raining = raining;
        this.sensorDTO = sensorDTO;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensorDTO;
    }

    public void setSensor(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }

    @Override
    public String toString() {
        return "\n" + "  Measurement {\n" +
                "    value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensorDTO +
                "  }" + "\n";
    }
}
