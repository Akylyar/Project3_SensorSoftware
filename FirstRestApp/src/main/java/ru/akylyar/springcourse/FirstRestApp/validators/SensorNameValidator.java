package ru.akylyar.springcourse.FirstRestApp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.akylyar.springcourse.FirstRestApp.models.Sensor;

@Component
public class SensorNameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensor.getName().isEmpty())
            errors.rejectValue("name", "", "Сенсора с таким именем не существует");
    }
}
