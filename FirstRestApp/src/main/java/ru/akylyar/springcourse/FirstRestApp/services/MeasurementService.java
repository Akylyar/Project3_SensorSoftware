package ru.akylyar.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akylyar.springcourse.FirstRestApp.models.Measurement;
import ru.akylyar.springcourse.FirstRestApp.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
       return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public Integer getRainyDaysCount() {
        List<Measurement> measurements = measurementsRepository.findAll();
        int count = 0;

        for (Measurement measurement : measurements) {
            if (measurement.isRaining())
                count++;
        }

        return count;
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasuredAt(LocalDateTime.now());
    }
}
