package com.example.theweather.presentation.main

import com.example.theweather.presentation.base.BaseRxPresenter
import com.example.theweather.presentation.model.CityModelView
import com.example.theweather.util.extensions.*
import com.example.theweather.util.service.UiUtil
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class WeatherPresenter(
    private val cityUseCase: WeatherUseCase,
    private val uiUtil: UiUtil,
    private val observeOnSchedulers: Scheduler
) : BaseRxPresenter<WeatherView>() {

    fun cityWeather(cityName: String) {
        if (uiUtil.isNetworkAvailable()) {
            val stream = cityUseCase.getCityWeather(CityModelView(cityName))
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnSchedulers)
                .wrapWithStreamWrapper()
                .share()

            stream.filterSuccess().receive()
                .subscribe { cityWeatherFromDb(cityName) }
                .disposeBy(bag)

            stream.filterError().toss()
                .subscribe { error -> view?.showError(error.localizedMessage) }
                .disposeBy(bag)
        }
    }

    private fun cityWeatherFromDb(city: String) {
        val stream = cityUseCase.getCityWeatherFromDb(CityModelView(city))
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnSchedulers)
            .wrapWithStreamWrapper()
            .share()

        stream.filterSuccess().receive()
            .subscribe { view?.showCityWeather(it) }
            .disposeBy(bag)

        stream.filterError().toss()
            .subscribe { error -> view?.showError(error.localizedMessage) }
            .disposeBy(bag)
    }

    fun allCitiesWeatherFromDb() {
        val stream = cityUseCase.getAllCitiesWeatherFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnSchedulers)
            .wrapWithStreamWrapper()
            .share()

        stream.filterSuccess().receive()
            .subscribe { view?.showAllCitiesWeather(it) }
            .disposeBy(bag)

        stream.filterError().toss()
            .subscribe { error -> view?.showError(error.localizedMessage) }
            .disposeBy(bag)
    }

    @Deprecated("Unused")
    fun allWeatherInfo() {
        cityUseCase.getAllWeatherInfoFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnSchedulers)
            .subscribe({ result ->
                           view?.showAllCityWeatherInfo(result)
                       },
                       {
                           view?.showError(it.localizedMessage)
                       }).disposeBy(bag)
    }
}