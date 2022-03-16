package com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.models.WeatherResponseModel
import com.example.retrofit.models2.weatherResponse2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.service?.getGroup()
            ?.enqueue(object : Callback<weatherResponse2> {
                override fun onResponse(
                    call: Call<weatherResponse2>,
                    response: Response<weatherResponse2>
                ) {
                    var array = response.body()?.let { it1 ->
                        ArrayAdapter(
                            this@MainActivity,
                            android.R.layout.simple_spinner_item,
                            it1.list
                        )
                    }
                    binding.spinner.adapter = array
                    binding.spinner.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {

                            override fun onNothingSelected(parent: AdapterView<*>?) {}

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                val group = response.body()!!.list[position]
                                city(group.name)
                            }
                        }
                }
                override fun onFailure(call: Call<weatherResponse2>, t: Throwable) {
                    Log.d("test", "onFail:${t?.message}")
                }
            })
    }

    fun city(pos: String) {
        App.service?.getWeatherByCity(pos)?.enqueue(object : Callback<WeatherResponseModel> {
            override fun onResponse(
                call: Call<WeatherResponseModel>,
                response: Response<WeatherResponseModel>
            ) {
                binding.showBtn.setOnClickListener {
                    binding.cardView.visibility = View.VISIBLE
                    binding.tvDegree.text = response.body()?.main?.temp.toString() + "°C"
                    Glide.with(this@MainActivity)
                        .load("http://openweathermap.org/img/wn/" + response.body()?.weather?.get(0)?.icon + "@2x.png")
                        .into(binding.weatherImage)
                }
            }
            override fun onFailure(call: Call<WeatherResponseModel>, t: Throwable) {
                Log.d("test2", "onFail:${t?.message}")
            }
        })
    }
}