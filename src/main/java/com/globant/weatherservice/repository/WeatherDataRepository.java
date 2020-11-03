package com.globant.weatherservice.repository;

import com.globant.weatherservice.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findAllByDate(Date date);

    List<WeatherData> findAllByDateBetween(
            Date dateStart,
            Date dateEnd);
}
