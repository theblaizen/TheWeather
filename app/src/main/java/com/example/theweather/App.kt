package com.example.theweather

import android.app.Application
import com.example.theweather.di.component.AppComponent
import com.example.theweather.di.component.DaggerAppComponent
import com.example.theweather.di.component.DaggerWeatherComponent
import com.example.theweather.di.component.WeatherComponent
import com.example.theweather.di.module.AppModule
import com.example.theweather.di.module.LocalStorageModule
import com.example.theweather.di.module.NetworkingModule
import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkingModule(NetworkingModule(resources.getString(R.string.api)))
            .localStorageModule(LocalStorageModule(this))
            .build()

        SQLiteOnWeb.init(this).start()
    }

    companion object {
        var component: AppComponent? = null
            private set
        var weatherComponent: WeatherComponent? = null
            private set

        fun createWeatherComponent() {
            weatherComponent = DaggerWeatherComponent.builder()
                .appComponent(component)
                .build()
        }

        fun recycleWeatherComponent() {
            weatherComponent = null
        }

    }
}