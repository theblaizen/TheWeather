package com.example.theweather.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.App
import com.example.theweather.R
import com.example.theweather.data.model.db.WeatherDataWithInfo
import com.example.theweather.data.model.db.WeatherInfo
import com.example.theweather.presentation.add_location.AddLocationDialog
import com.example.theweather.presentation.add_location.OnAddLocationListener
import com.example.theweather.presentation.city_detail.CityDetailActivity
import com.example.theweather.util.Const
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), WeatherView {

    private lateinit var citiesList: RecyclerView
    private lateinit var cityWeatherAdapter: CityWeatherAdapter
    private var weatherList: ArrayList<WeatherDataWithInfo> = ArrayList()
    private lateinit var defaultLocations: ArrayList<String>

    @Inject
    protected lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()

        initCityListView()

        presenter.allCitiesWeatherFromDb()
    }

    private fun inject() {
        App.createWeatherComponent()
        App.weatherComponent?.inject(this)
    }

    private fun findViews() {
        citiesList = rv_cities
    }

    private fun initCityListView() {
        cityWeatherAdapter = CityWeatherAdapter(weatherList) { position ->
            val intent = Intent(this@MainActivity, CityDetailActivity::class.java)
            intent.putExtra(Const.WEATHER_DETAIL, weatherList[position])
            startActivity(intent)
        }

        citiesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cityWeatherAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.location_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.add_location -> {
            val fragmentManager = supportFragmentManager.beginTransaction()
            val dialog = AddLocationDialog.newInstance()
            dialog.setOnAddLocationListener(object : OnAddLocationListener {
                override fun onAddClicked(location: String) {
                    presenter.cityWeather(location)
                }
            })
            dialog.show(fragmentManager, "add_location_dialog")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun showCityWeather(data: WeatherDataWithInfo) {
        weatherList.add(data)
        cityWeatherAdapter.notifyDataSetChanged()
    }

    override fun showAllCitiesWeather(data: List<WeatherDataWithInfo>) {
        weatherList.addAll(data)
        if (data.isNotEmpty()) {
            weatherList.clear()
            for (city: WeatherDataWithInfo in data) {
                presenter.cityWeather(city.weatherData.name)
            }
        } else {
            defaultLocations =
                resources.getStringArray(R.array.default_locations).toCollection(ArrayList())
            defaultLocations.forEach { name ->
                presenter.cityWeather(name)
            }
        }
    }

    override fun showAllCityWeatherInfo(data: List<WeatherInfo>) {
        // handle all city weather info from database
    }

    override fun showCityWeatherInfo(data: WeatherInfo) {
        // handle city weather info from database
    }

    override fun showError(reason: String) {
        Toast.makeText(this, resources.getString(R.string.location_name_error), Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }

    override fun onDestroy() {
        App.recycleWeatherComponent()
        super.onDestroy()
    }
}
