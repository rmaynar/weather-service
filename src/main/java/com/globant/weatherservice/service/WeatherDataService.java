package com.globant.weatherservice.service;

import com.globant.weatherservice.dto.WeatherDataDTO;

import java.time.LocalDate;
import java.util.List;

public interface WeatherDataService {

  public WeatherDataDTO save(WeatherDataDTO weatherData);

  public WeatherDataDTO saveOrFail(WeatherDataDTO weatherData);

  public List<WeatherDataDTO> findAll();

  public List<WeatherDataDTO> findAllByDate(LocalDate date);

  public List<WeatherDataDTO> findAllByDateBetween(LocalDate dateStart, LocalDate dateEnd);

  public void deleteAll();
}
