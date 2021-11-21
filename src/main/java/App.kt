import di.DaggerAppComponent
import di.NetworkModule
import service.di.DaggerWeatherConfigurator_WeatherComponent
import service.di.WeatherConfigurator

/**
 * Класс для инициализации сущностей
 */
class App {

    private val appComponent = DaggerAppComponent.builder()
        .networkModule(NetworkModule())
        .build()

    val weatherComponent = DaggerWeatherConfigurator_WeatherComponent.builder()
        .appComponent(appComponent)
        .weatherModule(WeatherConfigurator.WeatherModule())
        .build()
}