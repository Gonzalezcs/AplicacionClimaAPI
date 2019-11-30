package com.example.colorweather.data.net

import com.example.colorweather.data.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyApi {

    @GET("forecast/{api_key}/{latitude},{longitude}")
    fun getWeather(
        @Path("api_key") api_key: String,
        @Path("latitude") latitude: String,
        @Path("longitude") longitude: String
    ): Call<Weather>
}