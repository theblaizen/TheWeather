package com.example.theweather.domain.usecase.city.mapper

import com.example.theweather.data.model.db.Clouds
import com.example.theweather.data.model.response.CloudsResponse
import com.example.theweather.domain.mapper.Mapper


object CloudsResponseToCloudsDataMapper : Mapper<CloudsResponse, Clouds> {
    override fun map(input: CloudsResponse) = Clouds(input.all)
}