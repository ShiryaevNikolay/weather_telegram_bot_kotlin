package di

import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent {
    fun getRetrofit(): Retrofit
}