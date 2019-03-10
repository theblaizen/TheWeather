package com.example.theweather.data.api

import com.example.theweather.data.model.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather?")
    fun getCityWeather(
        @Query("q") cityName: String,
        @Query("appid") key: String
    ): Single<WeatherResponse>
}