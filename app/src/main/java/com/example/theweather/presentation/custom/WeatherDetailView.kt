package com.example.theweather.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.theweather.R
import kotlinx.android.synthetic.main.view_weather_details.view.*

class WeatherDetailView : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        View.inflate(context, R.layout.view_weather_details, this)
    }

    fun setLocationName(name: String) {
        tv_location_name.text = name
    }

    fun setWeatherMainInfo(info: String) {
        tv_weather_info.text = info
    }

    fun setHumidityScore(score: String) {
        tv_humidity.text = score
    }

    fun setPressureScore(score: String) {
        tv_pressure.text = score
    }

    fun setWindScore(score: String) {
        tv_wind.text = score
    }

    fun setTemperatureScore(score: String) {
        tv_temperature.text = score
    }

    fun setForecastImage(url: String) {
        Glide.with(this).load(url)
            .into(iv_weather_icon)
    }
}