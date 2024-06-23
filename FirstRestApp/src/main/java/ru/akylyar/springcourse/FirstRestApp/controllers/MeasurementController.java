package ru.akylyar.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akylyar.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.akylyar.springcourse.FirstRestApp.models.Measurement;
import ru.akylyar.springcourse.FirstRestApp.services.MeasurementService;
import ru.akylyar.springcourse.FirstRestApp.util.ErrorsUtil;
import ru.akylyar.springcourse.FirstRestApp.util.MeasurementErrorResponse;
import ru.akylyar.springcourse.FirstRestApp.util.MeasurementException;
import ru.akylyar.springcourse.FirstRestApp.validators.SensorNameValidator;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final SensorNameValidator sensorNameValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, SensorNameValidator sensorNameValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorNameValidator = sensorNameValidator;
    }

    @GetMapping
    public List<Measurement> getMeasurements() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult bindingResult) {
        sensorNameValidator.validate(convertToMeasurement(measurementDTO).getSensor(), bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorsUtil.buildErrorMessage(bindingResult);
        }

        measurementService.save(convertToMeasurement(measurementDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
