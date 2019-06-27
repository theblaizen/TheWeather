package com.example.theweather.domain.usecase.city

import android.util.Log
import com.example.theweather.data.model.db.*
import com.example.theweather.domain.model.CityDomainModel
import com.example.theweather.domain.usecase.local_storage.LocalStorageGateway
import com.example.theweather.presentation.main.WeatherUseCase
import com.example.theweather.presentation.model.CityModelView
import com.example.theweather.util.Const
import io.reactivex.Completable
import io.reactivex.Single

class WeatherUseCaseImpl(
    private val weatherGateway: WeatherGateway,
    private val localStorageGateway: LocalStorageGateway
) : WeatherUseCase {

    override fun getCityWeather(model: CityModelView): Completable {
        val cityWeatherModel = CityDomainModel(model.cityName)
        return weatherGateway.cityWeather(cityWeatherModel)
            .doOnSuccess { data ->
                localStorageGateway.saveCityWeather(
                    WeatherData(
                        dt = data.dt,
                        name = data.name,
                        cod = data.cod,
                        main = Main(
                            temp = data.main.temp,
                            tempMin = data.main.tempMin,
                            humidity = data.main.humidity,
                            pressure = data.main.pressure,
                            tempMax = data.main.tempMax
                        ),
                        clouds = Clouds(
                            all = data.clouds.all
                        ),
                        id = data.id,
                        base = data.base,
                        wind = Wind(
                            deg = data.wind.deg,
                            speed = data.wind.speed
                        )
                    )
                )
                if (data.weatherInfo.isNotEmpty()) {
                    data.weatherInfo.forEach {
                        localStorageGateway.saveWeatherInfo(
                            WeatherInfo(
                                weatherId = it.weatherId,
                                main = it.main,
                                desciption = it.desciption,
                                icon = it.icon,
                                locationId = data.id,
                                locationName = data.name
                            )
                        )
                    }
                }
            }.doOnError {
                Log.d(Const.LOG_DEBUG, "", it)
            }
            .ignoreElement()
    }

    override fun getCityWeatherFromDb(model: CityModelView): Single<WeatherDataWithInfo> {
        val cityWeatherModel = CityDomainModel(model.cityName)
        return localStorageGateway.cityWeather(cityWeatherModel)
    }

    override fun getWeatherInfoFromDb(model: CityModelView): Single<WeatherInfo> {
        val cityWeatherModel = CityDomainModel(model.cityName)
        return localStorageGateway.weatherInfo(cityWeatherModel)
    }

    override fun getAllCitiesWeatherFromDb(): Single<List<WeatherDataWithInfo>> {
        return localStorageGateway.citiesWeather()
    }

    override fun getAllWeatherInfoFromDB(): Single<List<WeatherInfo>> {
        return localStorageGateway.allWeatherInfo()
    }

}