package com.example.schedulertest.Network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application() {

    private var BASE_URL = "https://192.168.1.7:3000" //통신하고자 하는 api서버의 기본 주소
    lateinit var networkService: NetworkService


    companion object {
        lateinit var instance: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }

    fun buildNetwork() {//Retrofit 객체 생성
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        networkService = retrofit.create(NetworkService::class.java) //Retrofit 객체 활성화

    }


}