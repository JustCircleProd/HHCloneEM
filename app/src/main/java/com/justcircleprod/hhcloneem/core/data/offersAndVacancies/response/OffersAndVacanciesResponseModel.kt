package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.response

data class OffersAndVacanciesResponseModel(
    val offers: List<OfferResponseModel>,
    val vacancies: List<VacancyResponseModel>
)