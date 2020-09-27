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
        private var component: AppComponent? = null
        private var weatherComponent: WeatherComponent? = null

        fun getAppWeatherComponent(): WeatherComponent? {
            return weatherComponent ?: DaggerWeatherComponent.builder()
                .appComponent(component)
                .build()
                .also { weatherComponent = it }
        }

        fun recycleWeatherComponent() {
            weatherComponent = null
        }
    }
}