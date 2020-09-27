package com.example.theweather.domain.usecase.city

import com.example.theweather.data.model.response.WeatherResponse
import com.example.theweather.domain.model.CityDomainModel
import io.reactivex.Single

interface WeatherGateway {
    fun cityWeather(cityWeatherModel: CityDomainModel): Single<WeatherResponse>
}