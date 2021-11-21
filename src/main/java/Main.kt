import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import service.repository.WeatherRepository

fun main() {
    val app = App()
    WeatherBot(app.weatherComponent.getWeatherRepository())
}

class WeatherBot(
    private val weatherRepository: WeatherRepository
) {

    private val bot: Bot

    init {
        bot = bot {
            token = WeatherBot.TOKEN
            dispatch {
                command("start") {
                    val result = bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Hello, Dagger2!"
                    )
                    result.fold({
                        // TODO: сделать что-нибудь с response
                    }, {
                        // TODO: что-то сделать при ошибке
                    })
                }
            }
        }
        bot.startPolling()
    }

    companion object {
        val TOKEN = "2146129739:AAEyZuPEuvRtDPvuKaiBrXoTls-K-0yU1fg"
    }
}