package com.example.theweather.data.repository.local_storage

import com.example.theweather.data.db.dao.CityWeatherDao
import com.example.theweather.data.db.dao.WeatherInfoDao
import com.example.theweather.data.model.db.WeatherData
import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.data.model.db.WeatherInfo
import com.example.theweather.domain.model.CityDomainModel
import com.example.theweather.domain.usecase.local_storage.LocalStorageGateway
import io.reactivex.Single

class LocalStorageGatewayImpl(
    private val cityWeatherDao: CityWeatherDao,
    private val weatherInfoDao: WeatherInfoDao
) :
    LocalStorageGateway {

    override fun cityWeather(cityWeatherModel: CityDomainModel): Single<WeatherDataWithInfo> {
        return cityWeatherDao.getCity(cityWeatherModel.cityName)
    }

    override fun citiesWeather(): Single<List<WeatherDataWithInfo>> {
        return cityWeatherDao.getAllCities()
    }

    override fun weatherInfo(cityWeatherModel: CityDomainModel): Single<WeatherInfo> {
        return weatherInfoDao.getCityWeatherInfo(cityWeatherModel.cityName)
    }

    override fun allWeatherInfo(): Single<List<WeatherInfo>> {
        return weatherInfoDao.getAllWeatherInfos()
    }

    override fun saveCityWeather(weatherData: WeatherData) {
        cityWeatherDao.insert(weatherData)
    }

    override fun saveWeatherInfo(weatherInfo: WeatherInfo) {
        weatherInfoDao.insert(weatherInfo)
    }
}