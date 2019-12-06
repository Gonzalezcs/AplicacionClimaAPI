package com.example.colorweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.colorweather.data.model.Weather
import com.example.colorweather.data.net.DarkSkyClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DarkSkyClient.getWeather().enqueue(object : Callback<Weather> {

            override fun onFailure(call: Call<Weather>, t: Throwable) {
               Log.d("MainActivity","Error")
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                Log.d("MainActivity","${response.body()?.currently?.summary}")
                descriptionTextView!!.setText("${response.body()?.currently?.summary}")
                minTempTextView!!.setText("${response.body()?.currently?.temperature?.roundToInt()}F")
                precipProbTextView!!.setText("${response.body()?.currently?.precipProbability?.roundToInt()}%")
                //iconImageView!!.setImageResource()

            }

        })
    }

    private fun getWeatherIcon(iconString: String) : Int {
        return when(iconString){
            "clear-day" -> R.drawable.clear_day
            "clear-night" -> R.drawable.clear_night
            "rain" -> R.drawable.rain
            "snow" -> R.drawable.snow
            "sleet" -> R.drawable.sleet
            "wind" -> R.drawable.wind
            "fog" -> R.drawable.fog
            "cloudy" -> R.drawable.cloudy
            "partly-cloudy-day" -> R.drawable.partly_cloudy_day
            "partly-cloudy-nigth" -> R.drawable.partly_cloudy_night
            "hail" -> R.drawable.hail
            "thunderstorm" -> R.drawable.thunderstorm
            else -> R.drawable.clear_day
        }
    }
}
