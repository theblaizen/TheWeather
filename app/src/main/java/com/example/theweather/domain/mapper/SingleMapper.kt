package com.example.theweather.domain.mapper

import io.reactivex.functions.Function


class SingleMapper<I, O>(private val mapper: Mapper<I, O>) : Function<I, O> {
    override fun apply(input: I): O {
        return mapper.map(input)
    }
}