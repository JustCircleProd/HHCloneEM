package com.justcircleprod.hhcloneem.presentation.components.bottomNavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.justcircleprod.hhcloneem.R
import com.justcircleprod.hhcloneem.presentation.components.NumberText
import com.justcircleprod.hhcloneem.presentation.components.TabText
import com.justcircleprod.hhcloneem.presentation.theme.Grey4
import com.justcircleprod.hhcloneem.presentation.theme.NoRippleConfiguration
import com.justcircleprod.hhcloneem.presentation.theme.Red
import com.justcircleprod.hhcloneem.presentation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBottomBar(
    navController: NavController,
    favouriteVacanciesCount: Int
) {
    val navigationBarItems = listOf(
        NavigationBottomBarItemModel.Search,
        NavigationBottomBarItemModel.Favourite(badgeCount = favouriteVacanciesCount),
        NavigationBottomBarItemModel.Responses,
        NavigationBottomBarItemModel.Messages,
        NavigationBottomBarItemModel.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    CompositionLocalProvider(LocalRippleConfiguration provides NoRippleConfiguration) {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.surface
        ) {
            navigationBarItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    alwaysShowLabel = true,
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != null && item.badgeCount != 0) {
                                    Badge(containerColor = Red) {
                                        NumberText(
                                            text = item.badgeCount.toString(),
                                            color = White
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(item.iconDrawableRes),
                                contentDescription = stringResource(item.titleStringRes),
                                modifier = Modifier.size(dimensionResource(R.dimen.default_icon_size))
                            )
                        }
                    },
                    label = {
                        TabText(text = stringResource(item.titleStringRes))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = Grey4,
                        unselectedTextColor = Grey4
                    ),
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}