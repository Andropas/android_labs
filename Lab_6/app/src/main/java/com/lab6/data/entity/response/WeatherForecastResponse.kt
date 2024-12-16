package com.lab6.data.entity.response

import com.lab6.data.entity.WeatherForecast

data class WeatherForecastResponse(
    val list: List<WeatherForecast>
)