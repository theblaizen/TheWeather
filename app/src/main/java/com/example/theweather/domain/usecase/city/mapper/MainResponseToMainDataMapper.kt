package com.example.theweather.domain.usecase.city.mapper

import com.example.theweather.data.model.db.Main
import com.example.theweather.data.model.response.MainResponse
import com.example.theweather.domain.mapper.Mapper


object MainResponseToMainDataMapper : Mapper<MainResponse, Main> {
    override fun map(input: MainResponse) = with(input) {
        Main(temp, tempMin, humidity, pressure, tempMax)
    }
}