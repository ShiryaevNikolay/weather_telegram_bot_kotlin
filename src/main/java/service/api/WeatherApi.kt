package service.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import service.response.CurrentWeatherResponse

interface WeatherApi {

    @GET(WeatherUrls.CURRENT_WEATHER)
    fun getCurrentWeather(
        @Query("q") cityName: String = CITY_DEFAULT,
        @Query("appid") appid: String = TOKEN,
        @Query("lang") lang: String = LANG_DEFAULT,
        @Query("units") units: String = UNITS_DEFAULT
    ): Single<CurrentWeatherResponse>

    companion object {
        private const val TOKEN = "e3b61d77078ab0540790b2d2042b23c5"
        private const val CITY_DEFAULT = "Voronezh"
        private const val LANG_DEFAULT = "ru"
        private const val UNITS_DEFAULT = "metric"
    }
}