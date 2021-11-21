package service.response

import com.google.gson.annotations.SerializedName

/**
 * Респонс текущего прогноза погоды
 */
class CurrentWeatherResponse(
    @SerializedName("weather")
    val weather: List<WeatherEntity>,
    @SerializedName("main")
    val main: MainEntity,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: WindEntity?,
    @SerializedName("rain")
    val rain: RainEntity?,
    @SerializedName("clouds")
    val clouds: CloudsEntity?,
    @SerializedName("name")
    val city: String
)

class WeatherEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

class MainEntity(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
)

class WindEntity(
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("deg")
    val deg: Int
)

class RainEntity(
    @SerializedName("1h")
    val time: Int
)

class CloudsEntity(
    @SerializedName("all")
    val all: Int
)