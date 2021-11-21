package bots

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
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

            /*
            Команда запуска бота
             */
            command("start") {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = """
                        Добро пожаловать, ${message.chat.firstName}!
                        Я периодически буду присылать прогноз погоды в наш чат :D
                        
                        Для получения справочной информации, отправь в сообщении `/help`
                    """.trimIndent()
                )
                getCurrentWeather({ currentWeather ->
                    val markdownText = getMessageForCurrentWeather(currentWeather)
                    val text = """
                        Опа, а вот и первый прогноз погоды на текущий момент! Можешь не благодарить :Р
                        
                        $markdownText
                    """.trimIndent()
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = text,
                        parseMode = ParseMode.MARKDOWN
                    )
                }, {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Не удалось получить данные"
                    )
                })
            }

            /*
            Команда для получения текущего прогноза погоды
             */
            command("weather") {
                getCurrentWeather({ currentWeather ->
                    val markdownText = getMessageForCurrentWeather(currentWeather)
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = markdownText,
                        parseMode = ParseMode.MARKDOWN
                    )
                }, {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Не удалось получить данные"
                    )
                })
            }

            /*
            Получение справочной информации
             */
            command("help") {
                val info = """
                    Доступные команды:
                    `/start` - запуск бота
                    `/weather` - поулчение прогноза погоды
                    `/help` - показать справочную информацию
                """.trimIndent()
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = info,
                    parseMode = ParseMode.MARKDOWN
                )
            }
        }
    }

    init {
        bot.startPolling()
    }

    /**
     * Получение текущего прогноза погоды
     */
    private fun getCurrentWeather(
        onSuccess: ((CurrentWeather) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        weatherRepository.getCurrentWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe(onSuccess, onError)
    }

    /**
     * Возвращает отформатированный тест с прогнозом погоды
     */
    private fun getMessageForCurrentWeather(weather: CurrentWeather): String {
        return """
            *${weather.city}*
            Погода: `${weather.weather[0].description}`
            Температура: `${weather.main.temp.toInt()} C`
            По ощущениям: `${weather.main.feelsLike.toInt()} C`
            Влажность: `${weather.main.humidity}%`
            ${
                weather.wind?.let { 
                    "Ветер: `${it.speed} м/с`"       
                }
            }
            Видимость: `${(weather.visibility / 1000)} км`
            Давление: `${weather.main.pressure} гПа`
        """.trimIndent()
    }

    companion object {
        val TOKEN = "2146129739:AAEyZuPEuvRtDPvuKaiBrXoTls-K-0yU1fg"
    }
}