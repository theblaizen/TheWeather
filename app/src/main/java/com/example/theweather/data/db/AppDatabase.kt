package com.example.theweather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.theweather.data.db.dao.CityWeatherDao
import com.example.theweather.data.db.dao.WeatherInfoDao
import com.example.theweather.data.model.db.WeatherData
import com.example.theweather.data.model.db.WeatherInfo

@Database(entities = [WeatherData::class, WeatherInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityWeatherDao(): CityWeatherDao

    abstract fun weatherInfoDao(): WeatherInfoDao

    companion object {
        val DATABASE_NAME: String = "city_weather.db"
    }
}