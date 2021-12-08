package com.globant.weatherservice.mapper;

import com.globant.weatherservice.dto.WeatherDataDTO;
import com.globant.weatherservice.model.WeatherData;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface WeatherDataMapper {
  WeatherDataDTO map(WeatherData weatherData);

  WeatherData map(WeatherDataDTO weatherDataDTO);
}
