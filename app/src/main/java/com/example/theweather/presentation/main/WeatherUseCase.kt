package com.example.theweather.presentation.main

import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.data.model.db.WeatherInfo
import com.example.theweather.presentation.model.CityModelView
import io.reactivex.Completable
import io.reactivex.Single

interface WeatherUseCase {
    fun getCityWeather(model: CityModelView): Completable
    fun getCityWeatherFromDb(model: CityModelView): Single<WeatherDataWithInfo>
    fun getAllCitiesWeatherFromDb(): Single<List<WeatherDataWithInfo>>
    fun getWeatherInfoFromDb(model: CityModelView): Single<WeatherInfo>
    fun getAllWeatherInfoFromDB(): Single<List<WeatherInfo>>
}