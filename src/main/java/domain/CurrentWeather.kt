package domain

/**
 * Объект текущего прогноза погоды
 */
class CurrentWeather(
    val weather: List<WeatherObj>,
    val main: MainObj,
    val visibility: Int,
    val wind: WindObj?,
    val rain: RainObj?,
    val clouds: CloudsObj?,
    val city: String
)

/**
 * Основная информация прогноза погоды
 */
class WeatherObj(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

/**
 * Содержит данные о температуре
 */
class MainObj(
    val temp: Float,
    val feelsLike: Float,
    val tempMin: Float,
    val tempMax: Float,
    val pressure: Int,
    val humidity: Int,
)

/**
 * Содержит данные о ветре
 */
class WindObj(
    val speed: Int,
    val deg: Int
)

/**
 * Содержит данные о дожде
 */
class RainObj(
    val time: Int
)

/**
 * Содержит данные об облачности
 */
class CloudsObj(
    val all: Int
)