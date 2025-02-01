package com.justcircleprod.hhcloneem.core.data.offersAndVacancies.mapper

import com.justcircleprod.hhcloneem.core.data.offersAndVacancies.response.VacancyResponseModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.AddressModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.ExperienceModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.SalaryModel
import com.justcircleprod.hhcloneem.core.domain.offersAndVacancies.model.VacancyModel

fun VacancyResponseModel.mapToDomainModel() =
    VacancyModel(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = AddressModel(
            house = address.house,
            street = address.street,
            town = address.town
        ),
        company = company,
        experience = ExperienceModel(
            previewText = experience.previewText,
            text = experience.text
        ),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = SalaryModel(
            short = salary.short,
            full = salary.full
        ),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )

fun List<VacancyResponseModel>.mapToDomainModels() =
    map { it.mapToDomainModel() }