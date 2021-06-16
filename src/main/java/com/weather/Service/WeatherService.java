package com.weather.Service;

import com.weather.Models.Weather;
import java.util.List;
import java.util.Optional;

public interface WeatherService {
    public List<Weather> findAll();
    public Optional<Weather> findByFcstDate(String fcstDate);
    public void deleteByFcstDate(String fcstDate);
    public Weather save(Weather weather);
    public void updateByFcstDate(String fcstDate, Weather weather);

    public List<Weather> insertWeather();
}
