package com.example.theweather.data.repository.city

import com.example.theweather.data.api.WeatherApi
import com.example.theweather.data.model.request.CityRequest
import com.example.theweather.data.model.response.WeatherResponse
import com.example.theweather.domain.model.CityDomainModel
import com.example.theweather.domain.usecase.city.WeatherGateway
import com.example.theweather.util.Const
import io.reactivex.Single

class WeatherGatewayImpl(private val weatherApi: WeatherApi) :
    WeatherGateway {

    override fun cityWeather(
        cityWeatherModel: CityDomainModel
    ): Single<WeatherResponse> {
        val model = CityRequest(cityWeatherModel.cityName)
        return weatherApi.getCityWeather(model.cityName, Const.API_KEY)
    }
}