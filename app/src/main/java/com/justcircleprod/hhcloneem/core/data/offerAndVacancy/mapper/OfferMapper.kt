package com.justcircleprod.hhcloneem.core.data.offerAndVacancy.mapper

import com.justcircleprod.hhcloneem.core.data.offerAndVacancy.response.OfferResponseModel
import com.justcircleprod.hhcloneem.core.domain.offerAndVacancy.model.OfferModel

fun OfferResponseModel.mapToDomainModel() =
    OfferModel(
        id = id,
        title = title,
        link = link,
        buttonText = button?.text
    )

fun List<OfferResponseModel>.mapToDomainModels() =
    map { it.mapToDomainModel() }