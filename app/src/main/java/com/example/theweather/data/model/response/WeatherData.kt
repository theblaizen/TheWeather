package com.example.theweather.data.model.response


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    var dt: Int = 0,
    var name: String = "",
    var cod: Int = 0,
    var main: MainResponse,
    var clouds: CloudsResponse,
    @SerializedName("weather")
    var weatherInfo: List<WeatherInfoResponse>,
    var id: Int = 0,
    var base: String = "",
    var wind: WindResponse
)

data class MainResponse(
    var temp: Double = 0.0,
    var tempMin: Double = 0.0,
    var humidity: Int = 0,
    var pressure: Int = 0,
    var tempMax: Double = 0.0
)

data class WindResponse(
    var deg: Double = 0.0,
    var speed: Double = 0.0
)

data class WeatherInfoResponse(
    @SerializedName("id")
    var weatherId: Int = 0,
    var main: String = "",
    var desciption: String = "",
    var icon: String = ""
)

data class CloudsResponse(
    var all: Double = 0.0
)