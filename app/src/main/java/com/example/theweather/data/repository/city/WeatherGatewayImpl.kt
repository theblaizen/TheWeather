package com.example.theweather.data.repository.city

import com.example.theweather.data.api.WeatherApi
import com.example.theweather.data.model.request.CityRequest
import com.example.theweather.domain.model.CityDomainModel
import com.example.theweather.domain.usecase.city.WeatherGateway
import com.example.theweather.util.Const

class WeatherGatewayImpl(private val weatherApi: WeatherApi) : WeatherGateway {
    override fun cityWeather(cityWeatherModel: CityDomainModel) =
        CityRequest(cityWeatherModel.cityName).run {
            weatherApi.getCityWeather(cityName, Const.API_KEY)
        }
}