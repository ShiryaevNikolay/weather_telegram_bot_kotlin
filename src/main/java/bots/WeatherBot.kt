package bots

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import domain.CurrentWeather
import io.reactivex.rxjava3.schedulers.Schedulers
import service.repository.WeatherRepository

/**
 * Бот, который показывает прогноз погоды
 */
class WeatherBot(
    private val weatherRepository: WeatherRepository
) {

    private val bot: Bot = bot {
        token = TOKEN
        dispatch {
            command("start") {
                val result = bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Hello here!"
                )
                getCurrentWeather({ currentWeather ->
                    println("Попали сюда")
                }, {
                    println("Произошла ошибка :(")
                })
//                result.fold({
//                    print(data)
//                    // TODO: сделать что-нибудь с response
//                }, {
//                    print(it)
//                    // TODO: что-то сделать при ошибке
//                })
            }
        }
    }

    init {
        bot.startPolling()
    }

    private fun getCurrentWeather(
        onSuccess: ((CurrentWeather) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        weatherRepository.getCurrentWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe(onSuccess, onError)
    }

    companion object {
        val TOKEN = "2146129739:AAEyZuPEuvRtDPvuKaiBrXoTls-K-0yU1fg"
    }
}