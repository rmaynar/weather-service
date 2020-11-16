package com.globant.weatherservice.controller;

import com.globant.weatherservice.model.WeatherData;
import com.globant.weatherservice.service.WeatherDataService;
import com.globant.weatherservice.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @PostMapping("/weather")
    public ResponseEntity<WeatherData> saveWeatherData(@RequestBody WeatherData weatherData){

        //Check if previously exists
        log.info("Checking for previous data");
        if(weatherData.getId() != 0 && weatherDataService.findById(weatherData.getId()).isPresent()){
            log.error("Weather data already exists!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        WeatherData weathersaved = weatherDataService.save(weatherData);
        return new ResponseEntity<>(weathersaved, HttpStatus.CREATED);
    }

    @GetMapping("/weather")
    public ResponseEntity<List> getAllWeatherData(@RequestParam(required = false) String date) throws ParseException {
        if(date != null){
            log.info("Returning all weather data filtered by date: " + date);
            Date inputDate = DateUtils.convertStrToDate(date, DateUtils.DATE_PATTERN_YYYY_MM_DD);
            log.info("Java formatted date: " + inputDate);
            List<WeatherData> filteredResult = weatherDataService.findAllByDate(inputDate);
            if(CollectionUtils.isEmpty(filteredResult)){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(filteredResult, HttpStatus.OK);
        }
        log.info("Returning all weather data");
        return new ResponseEntity<>(weatherDataService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/weather/temp")
    public ResponseEntity<List> getMaxAndMinTemperaturePerLocation(@RequestParam String startDate, @RequestParam String endDate
            , HttpServletResponse response) throws ParseException {
        Date start = null;
        Date end = null;
        if(startDate == null && endDate == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(startDate != null){
            start = DateUtils.convertStrToDate(startDate, DateUtils.DATE_PATTERN_YYYY_MM_DD);
        }
        if(endDate != null){
            end = DateUtils.convertStrToDate(endDate, DateUtils.DATE_PATTERN_YYYY_MM_DD);
        }
        return new ResponseEntity<>(weatherDataService.findAllByDateBetween(start, end), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity deleteAllRecords(){
        log.info("Deleting all the records");
        weatherDataService.deleteAll();
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
