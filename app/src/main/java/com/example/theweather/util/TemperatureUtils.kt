package com.example.theweather.util

fun Double.toCelcius() : String {
    val translatedTemperature = (this - Const.KELVIN_DIFF).toInt()
    return "$translatedTemperature °C"
}