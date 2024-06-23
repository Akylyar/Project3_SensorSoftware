package ru.akylyar.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akylyar.springcourse.FirstRestApp.dto.SensorDTO;
import ru.akylyar.springcourse.FirstRestApp.models.Sensor;
import ru.akylyar.springcourse.FirstRestApp.services.SensorService;
import ru.akylyar.springcourse.FirstRestApp.util.ErrorsUtil;
import ru.akylyar.springcourse.FirstRestApp.util.SensorErrorResponse;
import ru.akylyar.springcourse.FirstRestApp.util.SensorNoCreatedException;
import ru.akylyar.springcourse.FirstRestApp.validators.SensorValidator;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorsUtil.buildErrorMessage(bindingResult);
        }

        sensorService.save(convertToSensor(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNoCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
