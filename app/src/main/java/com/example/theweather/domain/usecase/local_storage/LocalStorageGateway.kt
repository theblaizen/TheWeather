package com.example.theweather.domain.usecase.local_storage

import com.example.theweather.data.model.db.WeatherData
import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.data.model.db.WeatherInfo
import com.example.theweather.domain.model.CityDomainModel
import io.reactivex.Single

interface LocalStorageGateway {
    fun cityWeather(cityWeatherModel: CityDomainModel): Single<WeatherDataWithInfo>

    fun citiesWeather(): Single<List<WeatherDataWithInfo>>

    fun weatherInfo(cityWeatherModel: CityDomainModel): Single<WeatherInfo>

    @Deprecated("Unused")
    fun allWeatherInfo(): Single<List<WeatherInfo>>

    fun saveCityWeather(weatherData: WeatherData)

    fun saveWeatherInfo(weatherInfo: WeatherInfo)
}