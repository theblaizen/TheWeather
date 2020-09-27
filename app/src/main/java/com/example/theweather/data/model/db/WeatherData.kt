package com.example.theweather.data.model.db

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "city_table")
data class WeatherData(
    var dt: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",
    var cod: Int = 0,
    @Embedded
    var main: Main,
    @Embedded
    var clouds: Clouds,
    @PrimaryKey
    var id: Int = 0,
    var base: String = "",
    @Embedded
    var wind: Wind
) : Serializable

data class Main(
    var temp: Double = 0.0,
    var tempMin: Double = 0.0,
    var humidity: Int = 0,
    var pressure: Int = 0,
    var tempMax: Double = 0.0
) : Serializable

data class Wind(
    var deg: Double = 0.0,
    var speed: Double = 0.0
) : Serializable


data class Clouds(
    var all: Double = 0.0
) : Serializable

@Entity(
    tableName = "weather_info",
    indices = [Index("locationId")],
    primaryKeys = ["weatherId", "locationId"],
    foreignKeys = [ForeignKey(
        entity = WeatherData::class,
        parentColumns = ["id"],
        childColumns = ["locationId"],
        onDelete = ForeignKey.CASCADE
    )]
)

open class WeatherInfo(
    var weatherId: Int = 0,
    var main: String = "",
    var desciption: String = "",
    var icon: String = "",
    @ColumnInfo(name = "locationId")
    var locationId: Int = 0,
    var locationName: String = ""
) : Serializable

object WeatherInfoDefault : WeatherInfo()

data class WeatherDataWithInfo(
    @Embedded
    var weatherData: WeatherData,
    @Relation(
        parentColumn = "name",
        entityColumn = "locationName"
    )
    var weatherInfo: List<WeatherInfo>
) : Serializable

