package com.example.theweather.presentation.city_detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.theweather.R
import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.util.Const
import com.example.theweather.util.Const.WEATHER_DETAIL
import kotlinx.android.synthetic.main.activity_city_detail.*

class CityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        initViews()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViews() {
        val weather: WeatherDataWithInfo =
            intent?.extras?.getSerializable(WEATHER_DETAIL) as WeatherDataWithInfo
        setupWeatherView(weather)
    }

    private fun setupWeatherView(weather: WeatherDataWithInfo) {
        v_weather_detail.apply {
            setLocationName(weather.weatherData.name)
            setHumidityScore(weather.weatherData.main.humidity.toString())
            setPressureScore(weather.weatherData.main.pressure.toString())
            setWindScore(weather.weatherData.wind.speed.toString())
            setTemperatureScore((weather.weatherData.main.temp - 273).toInt().toString())
        }.also { view ->
            if (weather.weatherInfo.isNotEmpty()) {
                view.setWeatherMainInfo(weather.weatherInfo[0].main)
                view.setForecastImage("${Const.FORECAST_IMAGES_URL}${weather.weatherInfo[0].icon}.png")
            }
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