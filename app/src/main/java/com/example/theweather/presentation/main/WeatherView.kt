package com.example.theweather.presentation.main

import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.data.model.db.WeatherInfo
import com.example.theweather.presentation.base.BaseView

interface WeatherView : BaseView {
    fun showCityWeather(data: WeatherDataWithInfo)
    fun showAllCitiesWeather(data: List<WeatherDataWithInfo>)
    fun showAllCityWeatherInfo(data: List<WeatherInfo>)
    fun showCityWeatherInfo(data: WeatherInfo)
}