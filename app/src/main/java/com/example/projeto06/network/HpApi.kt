package com.example.projeto06.network


import com.example.projeto06.data.Ator
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://hp-api.herokuapp.com/"    // variavel com link base da api

private val moshi = Moshi.Builder()         //Conversão de json para os objetos
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()         //Responsavel por fazer os Gets
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)                                           //passando url base
    .build()

interface HpApiServicee {
    @GET("api/characters")                         // passar url de onde sera obtido o Get
    suspend fun getAtor(): List<Ator>         // Retorna uma lista do arquivo json convertido no mochi
}

object HpApi {
    val retrofitService: HpApiServicee by lazy{
        retrofit.create(HpApiServicee::class.java)
    }
}