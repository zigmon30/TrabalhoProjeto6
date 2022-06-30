package com.example.projeto06.network

import com.example.projeto06.data.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.opendota.com"    // variavel com link base da api

private val moshi = Moshi.Builder()         //Conversão de json para os objetos
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()         //Responsavel por fazer os Gets
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)                                           //passando url base
    .build()

interface OpenDotaApiService {
    @GET("api/heroStats")                         // passar url de onde sera obtido o Get
    suspend fun getHeroes(): List<Hero>           // Retorna uma lista do arquivo json convertido no mochi
}

object OpenDotaApi {
    val retrofitService: OpenDotaApiService by lazy{
        retrofit.create(OpenDotaApiService::class.java)
    }
}