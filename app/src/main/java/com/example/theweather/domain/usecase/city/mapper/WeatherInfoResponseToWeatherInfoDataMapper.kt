package com.example.theweather.domain.usecase.city.mapper

import com.example.theweather.data.model.db.WeatherInfo
import com.example.theweather.data.model.response.WeatherInfoResponse
import com.example.theweather.domain.mapper.Mapper


class WeatherInfoResponseToWeatherInfoDataMapper(
    private val id: Int,
    private val name: String
) : Mapper<WeatherInfoResponse, WeatherInfo> {
    override fun map(input: WeatherInfoResponse) = with(input) {
        WeatherInfo(weatherId, main, desciption, icon, id, name)
    }
}