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
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wtNo;

    @Column
    private String id;

    @Column
    private String name;

    private Weather() { // 기본 생성자 추가 
    }

    public Weather(Long wtNo, String id, String name) {
        this.wtNo = wtNo;
        this.id = id;
        this.name = name;
    }

    public Long getWtNo() {
        return wtNo;
    }

    public void setWtNo(Long wtNo) {
        this.wtNo = wtNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
