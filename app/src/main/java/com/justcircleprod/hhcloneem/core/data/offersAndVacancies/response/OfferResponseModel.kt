package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.response

data class OfferResponseModel(
    val id: String?,
    val link: String,
    val title: String,
    val button: Button?
)

data class Button(
    val text: String
)