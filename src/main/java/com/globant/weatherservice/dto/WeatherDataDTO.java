package com.globant.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globant.weatherservice.model.Location;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Builder
@Value
public class WeatherDataDTO {
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate date;

  private Location location;
  private List<Double> temperature;
}
