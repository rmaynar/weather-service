package com.globant.weatherservice.service;

import com.globant.weatherservice.model.WeatherData;
import com.globant.weatherservice.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class WeatherDataServiceImpl implements WeatherDataService{

    @Autowired
    private WeatherDataRepository weatherDataRepository;


    @Override
    public WeatherData save(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }

    @Override
    public Optional<WeatherData> findById(Long id) {
        return  weatherDataRepository.findById(id);
    }

    @Override
    public List<WeatherData> findAll() {
        return weatherDataRepository.findAll();
    }

    @Override
    public List<WeatherData> findAllByDate(Date date) {
        return weatherDataRepository.findAllByDate(date);
    }

    @Override
    public List<WeatherData> findAllByDateBetween(Date dateStart, Date dateEnd) {
        return weatherDataRepository.findAllByDateBetween(dateStart, dateEnd);
    }

    @Override
    public void deleteAll() {
        weatherDataRepository.deleteAll();
    }
}
