package com.example.colorweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.colorweather.data.model.Currently
import com.example.colorweather.data.model.Weather
import com.example.colorweather.data.net.DarkSkyClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeather()

    }

    private fun getWeather(){
        displayProgressBar(true)
        displayUI(false)
        DarkSkyClient.getWeather().enqueue(object : Callback<Weather> {

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("MainActivity","Error log")
                displayProgressBar(false)
                displayErrorMessage()
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                displayProgressBar(false)
                displayUI(true)
                setUpWidgets(response.body()?.currently)
            }

        })
    }

    private fun displayProgressBar(display : Boolean){
        progressBar!!.visibility = if (display) View.VISIBLE else View.GONE
    }

    private fun displayUI(visible: Boolean){
        dateTextView!!.visibility = if(visible) View.VISIBLE else View.GONE
        iconImageView!!.visibility = if(visible) View.VISIBLE else View.GONE
        descriptionTextView!!.visibility = if(visible) View.VISIBLE else View.GONE
        minTempTextView!!.visibility = if(visible) View.VISIBLE else View.GONE
        precipProbTextView!!.visibility = if(visible) View.VISIBLE else View.GONE
        dailyButton!!.visibility = if(visible) View.VISIBLE else View.GONE


    }

    private fun displayErrorMessage(){
        Toast.makeText(this,"Error de conexión. Intentar en unos minutos", Toast.LENGTH_LONG).show()
    }

    private fun setUpWidgets(currently: Currently?){
        Log.d("MainActivity","${currently?.summary}")
        descriptionTextView!!.setText("${currently?.summary}")
        minTempTextView!!.setText("${currently?.temperature?.roundToInt()}F")
        precipProbTextView!!.setText("${currently?.precipProbability?.roundToInt()}%")
        iconImageView!!.setImageResource(getWeatherIcon(currently?.icon ?: "clear_day"))
        dateTextView!!.setText(getDateTime()?.capitalize() ?: "Sin datos ):")
        // de prueba abajo para ir probando cada uno :D
        //iconImageView!!.setImageResource(getWeatherIcon("clear-night"))
    }

    private fun getDateTime(): String?{
        return try{
            val simpleDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
            val date = Calendar.getInstance().time
            simpleDateFormat.format(date)

        }catch (e: Exception){
            e.toString()
        }

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
