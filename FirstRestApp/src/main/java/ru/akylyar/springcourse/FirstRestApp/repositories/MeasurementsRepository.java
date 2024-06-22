package ru.akylyar.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akylyar.springcourse.FirstRestApp.models.Measurement;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
