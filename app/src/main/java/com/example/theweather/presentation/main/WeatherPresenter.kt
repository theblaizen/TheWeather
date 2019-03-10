package com.example.theweather.presentation.main

import com.example.theweather.presentation.base.BasePresenter
import com.example.theweather.presentation.model.CityModelView
import com.example.theweather.util.disposeBy
import com.example.theweather.util.service.UiUtil
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherPresenter(
    private val cityUseCase: WeatherUseCase,
    private val uiUtil: UiUtil,
    private val observeOnSchedulers: Scheduler
) : BasePresenter<WeatherView>() {

    private val disposable = CompositeDisposable()

    fun cityWeather(cityName: String) {
        if (uiUtil.isNetworkAvailable()) {
            val cityWeatherResponse = cityUseCase.getCityWeather(CityModelView(cityName))
            cityWeatherResponse
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnSchedulers)
                .subscribe({
                        cityWeatherFromDb(cityName)
                    },
                    { error ->
                        view?.showError(error.localizedMessage)
                    }
                ).disposeBy(disposable)

        }
    }

    fun cityWeatherFromDb(city: String) {
        cityUseCase.getCityWeatherFromDb(CityModelView(city))
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnSchedulers)
            .subscribe({ result ->
                           view?.showCityWeather(result)
                       },
                       {
                           view?.showError(it.localizedMessage)
                       }).disposeBy(disposable)
    }

    fun allCitiesWeatherFromDb() {
        cityUseCase.getAllCitiesWeatherFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnSchedulers)
            .subscribe(
                { result ->
                    view?.showAllCitiesWeather(result)
                },
                { error ->
                    view?.showError(error.localizedMessage)
                }
            ).disposeBy(disposable)

    }

    fun allWeatherInfo() {
        cityUseCase.getAllWeatherInfoFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnSchedulers)
            .subscribe({ result ->
                           view?.showAllCityWeatherInfo(result)
                       },
                       {
                           view?.showError(it.localizedMessage)
                       }).disposeBy(disposable)
    }

    override fun detachView(view: WeatherView) {
        disposable.clear()
        super.detachView(view)
    }
}