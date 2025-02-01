package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.mapper

import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.response.OfferResponseModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.OfferModel

fun OfferResponseModel.mapToDomainModel() =
    OfferModel(
        id = id,
        title = title,
        link = link,
        buttonText = button?.text
    )

fun List<OfferResponseModel>.mapToDomainModels() =
    map { it.mapToDomainModel() }