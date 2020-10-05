package com.example.theweather.domain.mapper


interface Mapper<I, O> {
    fun map(input: I): O
}