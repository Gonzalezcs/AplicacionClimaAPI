package com.example.colorweather.data.model

data class Minutely(

    val summary: String,
    val icon: String,
    val data: List<Data>
)