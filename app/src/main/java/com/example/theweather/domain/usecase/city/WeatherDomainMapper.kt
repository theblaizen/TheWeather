package com.example.theweather.domain.usecase.city

import com.example.theweather.data.model.db.*
import com.example.theweather.data.model.response.WeatherResponse
import io.reactivex.functions.Function


object WeatherDomainMapper : Function<WeatherResponse, Pair<WeatherData, WeatherInfo>> {
    override fun apply(data: WeatherResponse): Pair<WeatherData, WeatherInfo> {
        return WeatherData(
            dt = data.dt,
            name = data.name,
            cod = data.cod,
            main = Main(
                temp = data.main.temp,
                tempMin = data.main.tempMin,
                humidity = data.main.humidity,
                pressure = data.main.pressure,
                tempMax = data.main.tempMax
            ),
            clouds = Clouds(
                all = data.clouds.all
            ),
            id = data.id,
            base = data.base,
            wind = Wind(
                deg = data.wind.deg,
                speed = data.wind.speed
            )
        ) to (data.weatherInfo.takeIf { data.weatherInfo.isNotEmpty() }
            ?.map {
                WeatherInfo(
                    weatherId = it.weatherId,
                    main = it.main,
                    desciption = it.desciption,
                    icon = it.icon,
                    locationId = data.id,
                    locationName = data.name
                )
            }?.first() ?: WeatherInfoDefault)
    }
}