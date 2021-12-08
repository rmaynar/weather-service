package com.globant.weatherservice.repository;

import com.globant.weatherservice.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

  List<WeatherData> findAllByDate(LocalDate date);

  List<WeatherData> findAllByDateBetween(LocalDate dateStart, LocalDate dateEnd);
}
