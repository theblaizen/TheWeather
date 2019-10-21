package com.example.theweather.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.R
import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.util.extensions.makeVisible
import com.example.theweather.util.toCelcius
import kotlinx.android.synthetic.main.item_city.view.*

class CityWeatherAdapter(
    private val items: ArrayList<WeatherDataWithInfo>,
    private val itemListener: (position: Int) -> Unit
) : RecyclerView.Adapter<CityWeatherAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setCityName(items[position].weatherData.name)
        holder.setCityTemperature(items[position].weatherData.main.temp)
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.tv_city_name
        private val temperatureInCity: TextView = itemView.tv_city_temperature

        init {
            itemView.setOnClickListener {
                if (adapterPosition >= 0) {
                    itemListener.invoke(adapterPosition)
                }
            }
        }

        fun setCityName(name: String) {
            cityName.text = name
        }

        fun setCityTemperature(temperature: Double) {
            temperatureInCity.text = temperature.toCelcius()
            temperatureInCity.makeVisible()
        }
    }
}