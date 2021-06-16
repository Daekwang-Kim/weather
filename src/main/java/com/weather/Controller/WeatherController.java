package com.weather.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.weather.Models.Weather;
import com.weather.Service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("WeatherTest")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Weather>> getAllWeathers() {
        List<Weather> Weather = weatherService.findAll();
        return new ResponseEntity<List<Weather>>(Weather, HttpStatus.OK);
    }

    @GetMapping(value = "/{fcstDate}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Weather> getWeather(@PathVariable("fcstDate") String fcstDate) {
        Optional<Weather> Weather = weatherService.findByFcstDate(fcstDate);
        return new ResponseEntity<Weather>(Weather.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fcstDate}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteWeather(@PathVariable("fcstDate") String fcstDate) {
        weatherService.deleteByFcstDate(fcstDate);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{fcstDate}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Weather> updateWeather(@PathVariable("fcstDate") String fcstDate, Weather Weather) {
        weatherService.updateByFcstDate(fcstDate, Weather);
        return new ResponseEntity<Weather>(Weather, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> save(Weather Weather) {
        return new ResponseEntity<Weather>(weatherService.save(Weather), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveWeather", method = RequestMethod.GET)
    public ResponseEntity<Weather> save(HttpServletRequest req, Weather Weather) {
        return new ResponseEntity<Weather>(weatherService.save(Weather), HttpStatus.OK);
    }

    @GetMapping(value = "makeTodayWeather")
    public ResponseEntity<List<Weather>> insertWeather() {
        List<Weather> Weather = weatherService.insertWeather();;
        return new ResponseEntity<List<Weather>>(Weather, HttpStatus.OK);
    }

}
