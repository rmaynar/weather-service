package com.globant.weatherservice.service;

import com.globant.weatherservice.model.WeatherData;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherDataService {

    public WeatherData save(WeatherData weatherData);

    public Optional<WeatherData> findById(Long id);

    public List<WeatherData> findAll();

    public List<WeatherData>findAllByDate(Date date);

    public List<WeatherData>findAllByDateBetween(Date dateStart, Date dateEnd);

    public void deleteAll();
}
