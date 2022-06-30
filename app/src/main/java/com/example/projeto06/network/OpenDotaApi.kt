package com.example.projeto06.network

import com.example.projeto06.data.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.opendota.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface OpenDotaApiService {
    @GET("api/heroStats")
    suspend fun getHeroes(): List<Hero>
}

object OpenDotaApi {
    val retrofitService: OpenDotaApiService by lazy{
        retrofit.create(OpenDotaApiService::class.java)
    }
}