package com.example.theweather.util.extensions

import com.example.theweather.util.Const

fun Double.toCelcius() : String {
    val translatedTemperature = (this - Const.KELVIN_DIFF).toInt()
    return "$translatedTemperature °C"
}