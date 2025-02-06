package com.justcircleprod.hhcloneem.presentation.components.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.presentation.components.Screen

sealed class NavigationBottomBarItemModel(
    val route: String,
    @DrawableRes val iconDrawableRes: Int,
    @StringRes val titleStringRes: Int,
    val badgeCount: Int? = null
) {

    data object Search :
        NavigationBottomBarItemModel(Screen.SEARCH.name, R.drawable.icon_search, R.string.search)

    class Favourite(badgeCount: Int?) :
        NavigationBottomBarItemModel(
            Screen.FAVOURITE_VACANCIES.name,
            R.drawable.icon_favourite_outlined,
            R.string.favourite,
            badgeCount
        )

    data object Responses :
        NavigationBottomBarItemModel(
            Screen.RESPONSES.name,
            R.drawable.icon_responses,
            R.string.responses
        )

    data object Messages :
        NavigationBottomBarItemModel(
            Screen.MESSAGES.name,
            R.drawable.icon_messages,
            R.string.messages
        )

    data object Profile :
        NavigationBottomBarItemModel(Screen.PROFILE.name, R.drawable.icon_profile, R.string.profile)
}