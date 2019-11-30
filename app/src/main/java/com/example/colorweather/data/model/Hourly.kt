package com.example.colorweather.data.model

data class Hourly(

    val summary: String,
    val icon: String,
    val data: List<Data>
)