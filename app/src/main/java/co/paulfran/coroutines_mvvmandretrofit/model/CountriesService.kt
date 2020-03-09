package co.paulfran.coroutines_mvvmandretrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CountriesService {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    fun getCountriesService(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
}