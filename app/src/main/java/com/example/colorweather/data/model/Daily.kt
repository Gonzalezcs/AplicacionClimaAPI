package com.example.colorweather.data.model

data class Daily(
    val summary: String,
    val icon: String,
    val data: List<Data>
)