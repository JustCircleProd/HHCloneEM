package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.dataSource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OffersAndVacanciesRetrofitClient {

    private const val BASE_URL = "https://drive.usercontent.google.com/"

    fun getInstance(): OffersAndVacanciesApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OffersAndVacanciesApi::class.java)
}