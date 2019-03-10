package com.example.theweather.di.component

import com.example.theweather.di.module.WeatherModule
import com.example.theweather.di.scope.WeatherScope
import com.example.theweather.presentation.main.MainActivity
import dagger.Component

@WeatherScope
@Component(
    modules = [
        WeatherModule::class
    ],
    dependencies = [
        AppComponent::class
    ]
)
interface WeatherComponent {
    fun inject(mainActivity: MainActivity)
}