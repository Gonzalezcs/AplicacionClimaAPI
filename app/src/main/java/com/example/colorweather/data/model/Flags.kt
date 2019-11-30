package com.example.colorweather.data.model

data class Flags(
    val sources: List<String>,
    val nearest_station: Double,
    val units: String
)