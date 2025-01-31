package com.justcircleprod.hhcloneem.core.presentation.components

enum class Screen {
    SEARCH, FAVOURITE, RESPONSES, MESSAGES, PROFILE
}

sealed class NavigationItem(val route: String) {

    data object Search : NavigationItem(Screen.SEARCH.name)

    data object Favourite : NavigationItem(Screen.FAVOURITE.name)

    data object Responses : NavigationItem(Screen.RESPONSES.name)

    data object Messages : NavigationItem(Screen.MESSAGES.name)

    data object Profile : NavigationItem(Screen.PROFILE.name)
}