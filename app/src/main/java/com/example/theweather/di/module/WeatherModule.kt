package com.example.theweather.di.module

import com.example.theweather.data.api.WeatherApi
import com.example.theweather.data.repository.city.WeatherGatewayImpl
import com.example.theweather.di.scope.WeatherScope
import com.example.theweather.domain.usecase.city.WeatherGateway
import com.example.theweather.domain.usecase.city.WeatherUseCaseImpl
import com.example.theweather.domain.usecase.local_storage.LocalStorageGateway
import com.example.theweather.presentation.main.WeatherPresenter
import com.example.theweather.presentation.main.WeatherUseCase
import com.example.theweather.util.Const
import com.example.theweather.util.service.UiUtil
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named

@Module
class WeatherModule {

    @WeatherScope
    @Provides
    fun provideCityApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    @WeatherScope
    @Provides
    fun provideCityGateway(weatherApi: WeatherApi): WeatherGateway {
        return WeatherGatewayImpl(weatherApi)
    }

    @WeatherScope
    @Provides
    fun provideCityUseCase(
        weatherGateway: WeatherGateway,
        localStorageGateway: LocalStorageGateway
    ): WeatherUseCase {
        return WeatherUseCaseImpl(weatherGateway, localStorageGateway)
    }

    @WeatherScope
    @Provides
    fun provideWeatherPresenter(
        weatherUseCase: WeatherUseCase,
        uiUtil: UiUtil,
        @Named(Const.MAIN_THREAD_SCHEDULER) observeOnSchedulers: Scheduler
    ): WeatherPresenter {
        return WeatherPresenter(weatherUseCase, uiUtil, observeOnSchedulers)
    }
}