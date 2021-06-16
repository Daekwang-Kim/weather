package com.weather.Models;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "weather")
@Table(indexes = {@Index(name = "idx_fcstDate",  columnList="fcstDate", unique = false)})
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "category")
    private WeatherCategories categories;

    @Column
    private String fcstDate;

    @Column
    private String fcstTime;

    @Column
    private Long fcstValue;

    public Weather() { // 기본 생성자 추가 
    }

    public WeatherCategories getCategories() {
        return categories;
    }

    public void setCategories(WeatherCategories categories) {
        this.categories = categories;
    }

    public String getFcstDate() {
        return fcstDate;
    }

    public void setFcstDate(String fcstDate) {
        this.fcstDate = fcstDate;
    }

    public String getFcstTime() {
        return fcstTime;
    }

    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    public Long getFcstValue() {
        return fcstValue;
    }

    public void setFcstValue(Long fcstValue) {
        this.fcstValue = fcstValue;
    }

    public Weather(WeatherCategories categories, String fcstDate, String fcstTime, Long fcstValue) {
        this.categories = categories;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.fcstValue = fcstValue;
    }

    
    
}
