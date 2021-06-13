package com.weather.Service;

import com.weather.Models.Weather;
import com.weather.Repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public List<Weather> findAll() {
        List<Weather> weathers = new ArrayList<>();
        weatherRepository.findAll().forEach(e -> weathers.add(e));
        return weathers;
    }

    public Optional<Weather> findById(Long wtNo) {
        Optional<Weather> weather = weatherRepository.findById(wtNo);
        return weather;
    }

    public void deleteById(Long wtNo) {
        weatherRepository.deleteById(wtNo);
    }

    public Weather save(Weather weather) {
        weatherRepository.save(weather);
        return weather;
    }

    public void updateById(Long wtNo, Weather weather) {
        Optional<Weather> e = weatherRepository.findById(wtNo);
        if (e.isPresent()) {
            e.get().setWtNo(weather.getWtNo());
            e.get().setId(weather.getId());
            e.get().setName(weather.getName());
            weatherRepository.save(weather);
        }
    }

}
