package com.lab6.data

import com.lab6.data.entity.response.WeatherForecastResponse
import com.lab6.data.entity.response.WeatherResponse
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("lang") language: String = "uk",
        @Query("appid") apiId: String = "3cdf910d13f60749aa2cb1a407142405",
        @Query("units") units: String = "metric"
    ): WeatherResponse

    @GET("/data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("lang") language: String = "ua",
        @Query("appid") apiId: String = "3cdf910d13f60749aa2cb1a407142405",
        @Query("units") units: String = "metric"
    ): WeatherForecastResponse
}