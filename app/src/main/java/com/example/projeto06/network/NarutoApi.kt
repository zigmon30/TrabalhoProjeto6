package com.example.projeto06.network


import com.example.projeto06.data.Personagem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://rf-naruto-api.herokuapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NarutoApiService {
    @GET("api/v1/shinobi")
    suspend fun getPersonagens(): List<Personagem>
}

object NarutoApi {
    val retrofitService: NarutoApiService by lazy{
        retrofit.create(NarutoApiService::class.java)
    }
}