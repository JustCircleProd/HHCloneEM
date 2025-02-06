package com.justcircleprod.hhcloneem.data.offerAndVacancy.dataSource.remote

import com.justcircleprod.hhcloneem.data.offerAndVacancy.response.OffersAndVacanciesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query


interface OfferAndVacancyApi {

    @GET("u/0/uc")
    suspend fun getOffersAndVacancies(
        @Query("id") id: String = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r",
        @Query("export") export: String = "download"
    ): OffersAndVacanciesResponseModel
}