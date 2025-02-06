package com.justcircleprod.hhcloneem.data.offerAndVacancy.response

data class VacancyResponseModel(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>
)

data class Address(
    val house: String,
    val street: String,
    val town: String
)

data class Experience(
    val previewText: String,
    val text: String
)

data class Salary(
    val short: String?,
    val full: String
)