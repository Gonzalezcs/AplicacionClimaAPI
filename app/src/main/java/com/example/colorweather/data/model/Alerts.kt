package com.example.colorweather.data.model

data class Alerts (
    val title: String,
    val regions: List<String>,
    val severity: String,
    val time: Int,
    val expires: Int,
    val description: String,
    val uri: String
)