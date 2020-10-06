package com.example.theweather.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.theweather.R
import kotlinx.android.synthetic.main.view_weather_details.view.*

class WeatherDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var weatherIcon: ImageView
    private lateinit var locationName: TextView
    private lateinit var weatherMainInfo: TextView
    private lateinit var humidityScore: TextView
    private lateinit var pressureScore: TextView
    private lateinit var windScore: TextView
    private lateinit var temperatureScore: TextView


    init {
        View.inflate(context, R.layout.view_weather_details, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViews()
    }

    private fun findViews() {
        weatherIcon = iv_weather_icon
        locationName = tv_location_name
        weatherMainInfo = tv_weather_info
        humidityScore = tv_humidity
        pressureScore = tv_pressure
        windScore = tv_wind
        temperatureScore = tv_temperature
    }

    fun setLocationName(name: String) {
        locationName.text = name
    }

    fun setWeatherMainInfo(info: String) {
        weatherMainInfo.text = info
    }

    fun setHumidityScore(score: String) {
        humidityScore.text = score
    }

    fun setPressureScore(score: String) {
        pressureScore.text = score
    }

    fun setWindScore(score: String) {
        windScore.text = score
    }

    fun setTemperatureScore(score: String) {
        temperatureScore.text = score
    }

    fun setForecastImage(url: String) {
        Glide.with(this).load(url).into(weatherIcon)
    }
}