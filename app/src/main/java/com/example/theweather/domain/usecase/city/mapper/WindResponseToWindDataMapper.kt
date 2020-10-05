package com.example.theweather.domain.usecase.city.mapper

import com.example.theweather.data.model.db.Wind
import com.example.theweather.data.model.response.WindResponse
import com.example.theweather.domain.mapper.Mapper


object WindResponseToWindDataMapper : Mapper<WindResponse, Wind> {
    override fun map(input: WindResponse) = Wind(input.deg, input.speed)
}