package com.weather.Service;

import com.weather.Models.Weather;
import java.util.List;
import java.util.Optional;

public interface WeatherService {
    public List<Weather> findAll();
    public Optional<Weather> findById(Long wtNo);
    public void deleteById(Long wtNo);
    public Weather save(Weather weather);
    public void updateById(Long wtNo, Weather weather);

    public List<Weather> insertWeather();
}
