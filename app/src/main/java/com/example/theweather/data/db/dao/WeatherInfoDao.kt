package com.example.theweather.data.db.dao

import androidx.room.*
import com.example.theweather.data.model.db.WeatherInfo
import io.reactivex.Single

@Dao
interface WeatherInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherInfo: WeatherInfo)

    @Query("DELETE FROM weather_info WHERE locationName =:name")
    fun remove(name: String)

    @Query("DELETE FROM weather_info")
    fun removeAll()

    @Transaction
    @Query("SELECT * FROM weather_info ORDER BY locationName ASC")
    fun getAllWeatherInfos(): Single<List<WeatherInfo>>

    @Transaction
    @Query("SELECT * FROM weather_info WHERE locationName = :name ")
    fun getCityWeatherInfo(name: String): Single<WeatherInfo>
}