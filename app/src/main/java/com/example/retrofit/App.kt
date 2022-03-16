package com.example.retrofit

import android.app.Application
import com.example.retrofit.Services.Api
import com.example.retrofit.Services.RetrofitClient
import retrofit2.Retrofit

class App : Application() {
    var retrofit: Retrofit? = null

    override fun onCreate() {
        super.onCreate()
        service = RetrofitClient.getClient().create(Api::class.java)
    }

    companion object {
        var service: Api? = null
    }
}