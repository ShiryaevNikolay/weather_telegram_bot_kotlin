package service.repository

import domain.*
import io.reactivex.rxjava3.core.Single
import service.api.WeatherApi
import service.response.*

/**
 * Репозиторий для работы с апи прогноза погоды
 */
class WeatherRepository(
    private val weatherApi: WeatherApi
) {

    /**
     * Получает текущий прогназ погоды
     */
    fun getCurrentWeather(): Single<CurrentWeather> {
        return weatherApi.getCurrentWeather().map { it.mapToDomain() }
    }

    private fun CurrentWeatherResponse.mapToDomain(): CurrentWeather {
        return CurrentWeather(
            weather = weather.map { it.mapToDomain() },
            main = main.mapToDomain(),
            visibility = visibility,
            wind = wind.mapToDomain(),
            rain = rain.mapToDomain(),
            clouds = clouds.mapToDomain(),
            city = city
        )
    }

    private fun WeatherEntity.mapToDomain(): WeatherObj {
        return WeatherObj(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
    }

    private fun MainEntity.mapToDomain(): MainObj {
        return MainObj(
            temp = temp,
            feelsLike = feelsLike,
            tempMin = tempMin,
            tempMax = tempMax,
            pressure = pressure,
            humidity = humidity
        )
    }

    private fun WindEntity.mapToDomain(): WindObj {
        return WindObj(
            speed = speed,
            deg = deg
        )
    }

    private fun RainEntity.mapToDomain(): RainObj {
        return RainObj(
            time = time
        )
    }

    private fun CloudsEntity.mapToDomain(): CloudsObj {
        return CloudsObj(
            all = all
        )
    }
}