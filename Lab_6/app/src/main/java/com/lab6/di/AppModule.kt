package com.lab6.di

import com.lab6.data.ServerApi
import com.lab6.ui.screens.current.WeatherScreenViewModel
import com.lab6.ui.screens.forecast.WeatherForecastScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org"

val appModule = module {

    single<ServerApi> {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder: OkHttpClient.Builder = client.newBuilder().addInterceptor(interceptor)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
            .create(ServerApi::class.java)
    }

    viewModel { WeatherScreenViewModel(get()) }
    viewModel { WeatherForecastScreenViewModel(get()) }
}