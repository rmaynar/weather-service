package com.globant.weatherservice.service;

import static java.util.Objects.isNull;

import com.globant.weatherservice.dto.WeatherDataDTO;
import com.globant.weatherservice.exception.ResourceAlreadyExistingException;
import com.globant.weatherservice.mapper.WeatherDataMapper;
import com.globant.weatherservice.model.WeatherData;
import com.globant.weatherservice.repository.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService {

  private final WeatherDataRepository weatherDataRepository;
  private final WeatherDataMapper weatherDataMapper;

  @Override
  public WeatherDataDTO save(WeatherDataDTO weatherDataDTO) {
    return weatherDataMapper.map(weatherDataRepository.save(weatherDataMapper.map(weatherDataDTO)));
  }

  @Override
  public WeatherDataDTO saveOrFail(WeatherDataDTO weatherDataDTO) {
    if (isNull(weatherDataDTO.getId())
        || weatherDataRepository.findById(weatherDataDTO.getId()).isEmpty()) {

      return save(weatherDataDTO);
    }

    throw new ResourceAlreadyExistingException("Weather data already exists");
  }

  @Override
  public List<WeatherDataDTO> findAll() {
    return weatherDataRepository.findAll().stream()
        .sorted(Comparator.comparingLong(WeatherData::getId))
        .map(weatherDataMapper::map)
        .collect(Collectors.toList());
  }

  @Override
  public List<WeatherDataDTO> findAllByDate(LocalDate date) {

    return weatherDataRepository.findAllByDate(date).stream()
        .sorted(Comparator.comparingLong(WeatherData::getId))
        .map(weatherDataMapper::map)
        .collect(Collectors.toList());
  }

  @Override
  public List<WeatherDataDTO> findAllByDateBetween(LocalDate dateStart, LocalDate dateEnd) {
    return weatherDataRepository.findAllByDateBetween(dateStart, dateEnd).stream()
        .sorted(Comparator.comparingLong(WeatherData::getId))
        .map(weatherDataMapper::map)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteAll() {
    weatherDataRepository.deleteAll();
  }
}
