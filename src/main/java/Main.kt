import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold

fun main() {
    val bot = bot {
        token = WeatherBot.TOKEN
        dispatch {
            command("start") {
                val result = bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Hello, WeatherBot!"
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

class WeatherBot {

    companion object {
        val TOKEN = "2146129739:AAEyZuPEuvRtDPvuKaiBrXoTls-K-0yU1fg"
    }
}