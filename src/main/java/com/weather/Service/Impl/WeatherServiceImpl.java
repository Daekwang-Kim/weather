package com.weather.Service.Impl;

import com.weather.Models.Weather;
import com.weather.Repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.weather.Service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
    final String SERVICE_KEY = "9Te05p7Fn0ny4cRw4rDQ7zUxHs9xRaSTjHdm0adE14Y8t569GkpzlGwng6FczKxrp4ib7KvyD8sY8MNj73SERA%3D%3D";

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

    public List<Weather> insertWeather() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(now);
        String inputLine;
        String buffer = "";
        
        List<Weather> weathers = new ArrayList<>();
        String url;
        HttpURLConnection connection;

        StringBuffer params = new StringBuffer();
        try {
            params.append("?serviceKey=").append(SERVICE_KEY);
            params.append("&numOfRows=").append("200");
            params.append("&pageNo=").append("1");
            params.append("&base_date=").append(date);
            params.append("&base_time=").append("0500");
            params.append("&nx=").append("62");
            params.append("&ny=").append("118");

            url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"+params;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url.toString());

                doc.getDocumentElement().normalize();
                System.out.println(doc.getDocumentElement().getNodeName());

                NodeList nList = doc.getElementsByTagName("item");
                System.out.println("파싱할 리스트 수 : "+ nList.getLength());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("AAA");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return weathers;        
    }

}