package com.example.theweather.domain.usecase.city.mapper

import com.example.theweather.data.model.db.WeatherData
import com.example.theweather.data.model.response.WeatherResponse
import com.example.theweather.domain.mapper.Mapper


object WeatherResponseToWeatherDataMapper : Mapper<WeatherResponse, WeatherData> {
    override fun map(input: WeatherResponse) = with(input) {
        WeatherData(
            dt,
            name,
            cod,
            MainResponseToMainDataMapper.map(main),
            CloudsResponseToCloudsDataMapper.map(clouds),
            id,
            base,
            WindResponseToWindDataMapper.map(wind)
        )
    }
}