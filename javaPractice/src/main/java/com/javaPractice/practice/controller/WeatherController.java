package com.javaPractice.practice.controller;
import com.imooc.weather.WeatherUtils;
import com.imooc.weather.impl.WeatherUtilsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {

    @GetMapping(value = "/weather")
    public List getWeather(
            @RequestParam("id") Integer id,
            @RequestParam("area") String area
    ) {
        System.out.println("查询最近天气预报:");
        System.out.println("输入1: 查询未来24小时天气预报") ;
        System.out.println("输入2: 查询未来3天天气预报") ;
        System.out.println("输入3: 查询未来7天天气预报") ;
        System.out.println("请输入您的选择:");
        if (id == 1) {
//            请输入城市名称查询未来24小时天气预报
            WeatherUtils weatherUtils = new WeatherUtilsImpl();
            List weatherList = weatherUtils.w24h("00198b16cbf7434ab7bb24cbb82c3063", area);
            return weatherList;
        } else {
//            测试
            List weatherList = new ArrayList();
            weatherList.add("id=2");
            return weatherList;
        }
    }
}
