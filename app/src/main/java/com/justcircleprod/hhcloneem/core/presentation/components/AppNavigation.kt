package com.justcircleprod.hhcloneem.core.presentation.components

enum class Screen {
    SEARCH, FAVOURITE
}

sealed class NavigationItem(val route: String) {

    data object Search : NavigationItem(Screen.SEARCH.name)

    data object Favourite : NavigationItem(Screen.FAVOURITE.name)
}