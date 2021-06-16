package com.weather.Models;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "weatherCategories")
public class WeatherCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String category;

    @Column
    private String categoryDes;

    public WeatherCategories() { // 기본 생성자 추가 
    }

    public WeatherCategories(String category, String categoryDes) {
        this.category = category;
        this.categoryDes = categoryDes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDes() {
        return categoryDes;
    }

    public void setCategoryDes(String categoryDes) {
        this.categoryDes = categoryDes;
    }  

}
