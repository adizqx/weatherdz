package com.example.retrofit.Services

import com.example.retrofit.models.WeatherResponseModel
import com.example.retrofit.models2.weatherResponse2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

//    @GET("data/2.5/weather?units=metric&APPID=837c85cce51b1460d9cf65c77c12d81d")
//    fun getWeather(
//        @Query("lat") latitude: Double,
//        @Query("lon") longitude: Double
//    ):Call<WeatherResponseModel>

    @GET("data/2.5/group?id=524901,703448,2643743,1528675,292223,1850144,3117735&units=metric&APPID=837c85cce51b1460d9cf65c77c12d81d")
    fun getGroup(
    ): Call<weatherResponse2>

    @GET("data/2.5/weather?&units=metric&appid=837c85cce51b1460d9cf65c77c12d81d")
    fun getWeatherByCity(
        @Query("q") cityName: String
    ): Call<WeatherResponseModel>
}