package com.example.projeto06.network

import com.example.projeto06.data.domain.Personagens
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://rf-naruto-api.herokuapp.com"    // variavel com link base da api

private val moshi = Moshi.Builder()         //Conversão de json para os objetos
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()         //Responsavel por fazer os Gets
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)                                           //passando url base
    .build()

interface OpenPokemonApiService {
    @GET("api/v1/shinobi")                         // passar url de onde sera obtido o Get
    suspend fun getPokemons(): List<Personagens>           // Retorna uma lista do arquivo json convertido no mochi
}

object OpenPokemonApi {
    val retrofitService: OpenPokemonApiService by lazy{
        retrofit.create(OpenPokemonApiService::class.java)
    }
}