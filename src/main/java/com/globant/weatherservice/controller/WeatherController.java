package com.globant.weatherservice.controller;

import com.globant.weatherservice.dto.WeatherDataDTO;
import com.globant.weatherservice.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherDataService weatherDataService;

  @PostMapping("/weather-data")
  public ResponseEntity<WeatherDataDTO> saveWeatherData(
      @RequestBody WeatherDataDTO weatherDataDTO) {

    log.info("Saving weather data for " + weatherDataDTO.toString());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(weatherDataService.saveOrFail(weatherDataDTO));
  }

  @GetMapping("/weather-data")
  public ResponseEntity<List<WeatherDataDTO>> getAllWeatherData(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {

    if (date.isEmpty()) {
      log.info("Returning all weather data");
      return ResponseEntity.ok(weatherDataService.findAll());
    }

    log.info("Returning all weather data filtered by date: " + date.get().toString());
    return ResponseEntity.ok(weatherDataService.findAllByDate(date.get()));
  }

  @GetMapping("/weather-data/temp")
  public ResponseEntity<List<WeatherDataDTO>> getMaxAndMinTemperaturePerLocation(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

    return ResponseEntity.ok(weatherDataService.findAllByDateBetween(startDate, endDate));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteAllRecords() {

    log.info("Deleting all the records");
    weatherDataService.deleteAll();

    return ResponseEntity.ok().build();
  }
}
