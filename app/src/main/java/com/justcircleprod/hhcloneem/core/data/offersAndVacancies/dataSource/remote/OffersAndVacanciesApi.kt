package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.dataSource.remote

import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.response.OffersAndVacanciesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query


interface OffersAndVacanciesApi {

    @GET("u/0/uc")
    suspend fun getOffersAndVacancies(
        @Query("id") id: String = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r",
        @Query("export") export: String = "download"
    ): OffersAndVacanciesResponseModel
}