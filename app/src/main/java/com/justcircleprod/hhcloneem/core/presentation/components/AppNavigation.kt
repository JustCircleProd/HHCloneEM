package com.justcircleprod.hhcloneem.core.presentation.components

enum class Screen {
    SEARCH, FAVOURITE_VACANCIES, RESPONSES, MESSAGES, PROFILE, VACANCY_DETAILS
}

sealed class NavigationItem(val route: String) {

    data object Search : NavigationItem(Screen.SEARCH.name)

    data object FavouriteVacancies : NavigationItem(Screen.FAVOURITE_VACANCIES.name)

    data object Responses : NavigationItem(Screen.RESPONSES.name)

    data object Messages : NavigationItem(Screen.MESSAGES.name)

    data object Profile : NavigationItem(Screen.PROFILE.name)

    data object VacancyDetails : NavigationItem(Screen.VACANCY_DETAILS.name)
}