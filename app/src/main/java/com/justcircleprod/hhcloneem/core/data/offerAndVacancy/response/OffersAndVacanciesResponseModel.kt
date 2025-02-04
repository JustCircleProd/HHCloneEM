package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.response

data class OffersAndVacanciesResponseModel(
    val offers: List<OfferResponseModel>,
    val vacancies: List<VacancyResponseModel>
)