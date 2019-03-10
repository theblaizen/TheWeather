package com.example.theweather.di.module

import android.content.Context
import androidx.room.Room
import com.example.theweather.data.db.AppDatabase
import com.example.theweather.data.db.dao.CityWeatherDao
import com.example.theweather.data.db.dao.WeatherInfoDao
import com.example.theweather.data.repository.local_storage.LocalStorageGatewayImpl
import com.example.theweather.domain.usecase.local_storage.LocalStorageGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideCityWeatherDao(appDatabase: AppDatabase) = appDatabase.cityWeatherDao()

    @Singleton
    @Provides
    fun provideWeatherInfoDao(appDatabase: AppDatabase) = appDatabase.weatherInfoDao()

    @Singleton
    @Provides
    fun provideLocalStorage(
        cityWeatherDao: CityWeatherDao,
        weatherInfoDao: WeatherInfoDao
    ): LocalStorageGateway {
        return LocalStorageGatewayImpl(cityWeatherDao, weatherInfoDao)
    }
}