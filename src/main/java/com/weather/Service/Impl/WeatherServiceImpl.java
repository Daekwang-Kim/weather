package com.weather.Service.Impl;

import com.weather.Models.Weather;
import com.weather.Repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

    public Optional<Weather> findByFcstDate(String fcstDate) {
        Optional<Weather> weather = weatherRepository.findByFcstDate(fcstDate);
        return weather;
    }

    public void deleteByFcstDate(String fcstDate) {
        weatherRepository.deleteByFcstDate(fcstDate);
    }

    public Weather save(Weather weather) {
        weatherRepository.save(weather);
        return weather;
    }

    public void updateByFcstDate(String fcstDate, Weather weather) {
        Optional<Weather> e = weatherRepository.findByFcstDate(fcstDate);
        if (e.isPresent()) {
            e.get().setCategories(weather.getCategories());
            e.get().setFcstDate(weather.getFcstDate());
            e.get().setFcstTime(weather.getFcstTime());
            e.get().setFcstValue(weather.getFcstValue());
            weatherRepository.save(weather);
        }
    }

    public List<Weather> insertWeather() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(now);
        
        List<Weather> weathers = new ArrayList<>();
        String url;

        StringBuffer params = new StringBuffer();
        try {
            params.append("?serviceKey=").append(SERVICE_KEY);
            params.append("&numOfRows=").append("50");
            params.append("&pageNo=").append("1");
            params.append("&base_date=").append(date);
            params.append("&base_time=").append("0500");
            params.append("&nx=").append("62");
            params.append("&ny=").append("118");

            url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"+params;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            try {
                Weather weather = new Weather();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url.toString());
                
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("item");

                for(int i = 0; i < nList.getLength(); i++) {
                    NodeList childrenNode = (NodeList) nList.item(i);
                    Element el = (Element) childrenNode;
                    System.out.println("-----------------------------");
                    System.out.println(getTagValue("category", el));
                    System.out.println(getTagValue("fcstDate", el));
                    System.out.println(getTagValue("fcstTime", el));
                    System.out.println(getTagValue("fcstValue", el));

                    weatherRepository.save(weather);
                }
                System.out.println("파싱할 리스트 수 : "+ nList.getLength());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("AAA");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return weathers;        
    }

    private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
}