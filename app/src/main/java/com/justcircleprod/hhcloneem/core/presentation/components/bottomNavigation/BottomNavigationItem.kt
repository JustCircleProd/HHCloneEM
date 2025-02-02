package com.justcircleprod.hhcloneem.core.presentation.components.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.core.presentation.components.Screen

sealed class BottomNavigationItem(
    val route: String,
    @DrawableRes val iconDrawableRes: Int,
    @StringRes val titleStringRes: Int,
    val badgeCount: Int? = null
) {

    data object Search :
        BottomNavigationItem(Screen.SEARCH.name, R.drawable.icon_search, R.string.search)

    data object Favourite :
        BottomNavigationItem(Screen.FAVOURITE.name, R.drawable.icon_favourite, R.string.favourite)

    data object Responses :
        BottomNavigationItem(Screen.RESPONSES.name, R.drawable.icon_responses, R.string.responses)

    data object Messages :
        BottomNavigationItem(Screen.MESSAGES.name, R.drawable.icon_messages, R.string.messages)

    data object Profile :
        BottomNavigationItem(Screen.PROFILE.name, R.drawable.icon_profile, R.string.profile)
}