import bots.WeatherBot

fun main() {
    val app = App()
    WeatherBot(app.weatherComponent.getWeatherRepository())
}