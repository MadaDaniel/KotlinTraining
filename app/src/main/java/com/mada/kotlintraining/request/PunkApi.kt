package com.mada.kotlintraining.request

import com.mada.kotlintraining.models.BeerFromPlugin
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface PunkApi {

    @GET("v2/beers")
    fun getBeers(): Call<BeerFromPlugin>

    companion object {

        private const val BASE_URL = "https://api.punkapi.com/"

        fun create(): PunkApi {
            val retrofit2 = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit2.create(PunkApi::class.java)
        }
    }
}