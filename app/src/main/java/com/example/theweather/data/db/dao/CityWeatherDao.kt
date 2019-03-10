package com.example.theweather.data.db.dao

import androidx.room.*
import com.example.theweather.data.model.db.WeatherData
import com.example.theweather.data.model.db.WeatherDataWithInfo
import io.reactivex.Single

@Dao
interface CityWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData: WeatherData)

    @Query("DELETE FROM city_table WHERE name =:cityName")
    fun remove(cityName: String)

    @Query("DELETE FROM city_table")
    fun removeAll()

    @Transaction
    @Query("SELECT * FROM city_table ORDER BY name ASC")
    fun getAllCities(): Single<List<WeatherDataWithInfo>>

    @Transaction
    @Query("SELECT * FROM city_table WHERE name = :locationName")
    fun getCity(locationName: String): Single<WeatherDataWithInfo>

}