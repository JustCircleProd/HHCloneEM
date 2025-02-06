package com.justcircleprod.hhcloneem.data.offerAndVacancy.dataSource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OfferAndVacancyRetrofitClient {

    private const val BASE_URL = "https://drive.usercontent.google.com/"

    fun getInstance(): OfferAndVacancyApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OfferAndVacancyApi::class.java)
}