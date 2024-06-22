package ru.akylyar.springcourse.FirstRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akylyar.springcourse.FirstRestApp.models.Sensor;
import ru.akylyar.springcourse.FirstRestApp.repositories.SensorsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorsRepository sensorsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorsRepository sensorsRepository, ModelMapper modelMapper) {
        this.sensorsRepository = sensorsRepository;
        this.modelMapper = modelMapper;
    }

    public List<Sensor> findSensors() {
        return sensorsRepository.findAll();
    }

    public Sensor findById(int id) {
        return sensorsRepository.findById(id).orElse(null);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
