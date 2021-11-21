package service.di

import dagger.Component
import dagger.Module
import dagger.Provides
import di.AppComponent
import retrofit2.Retrofit
import service.api.WeatherApi
import service.repository.WeatherRepository
import javax.inject.Singleton

/**
 * Конфигуратор иверсии зависимости
 */
class WeatherConfigurator {

    @Singleton
    @Component(
        dependencies = [AppComponent::class],
        modules = [WeatherModule::class]
    )
    interface WeatherComponent {
        fun getWeatherRepository(): WeatherRepository
    }

    @Module
    internal class WeatherModule {

        @Provides
        fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
            return retrofit.create(WeatherApi::class.java)
        }
    }
}