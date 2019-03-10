package com.example.theweather.presentation.city_detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.theweather.R
import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.presentation.custom.WeatherDetailView
import com.example.theweather.util.Const
import com.example.theweather.util.Const.WEATHER_DETAIL
import kotlinx.android.synthetic.main.activity_city_detail.*

class CityDetailActivity : AppCompatActivity() {

    private lateinit var weatherDetailView: WeatherDetailView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        findViews()
        initViews()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun findViews() {
        weatherDetailView = v_weather_detail
    }

    private fun initViews() {
        val weather: WeatherDataWithInfo =
            intent?.extras?.getSerializable(WEATHER_DETAIL) as WeatherDataWithInfo
        weatherDetailView.setLocationName(weather.weatherData.name)
        weatherDetailView.setHumidityScore(weather.weatherData.main.humidity.toString())
        weatherDetailView.setPressureScore(weather.weatherData.main.pressure.toString())
        weatherDetailView.setWindScore(weather.weatherData.wind.speed.toString())
        weatherDetailView.setTemperatureScore((weather.weatherData.main.temp - 273).toInt().toString())

        if (weather.weatherInfo.isNotEmpty()) {
            weatherDetailView.setWeatherMainInfo(weather.weatherInfo[0].main)
            weatherDetailView.setForecastImage("${Const.FORECAST_IMAGES_URL}${weather.weatherInfo[0].icon}.png")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}