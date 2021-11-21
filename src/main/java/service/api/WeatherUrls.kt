package service.api

/**
 * Хранит пути для получения данных с сайта [https://openweathermap.org/]
 */
object WeatherUrls {
    private const val BASE = "https://api.openweathermap.org/data/2.5"
    const val CURRENT_WEATHER = "$BASE/weather"
}