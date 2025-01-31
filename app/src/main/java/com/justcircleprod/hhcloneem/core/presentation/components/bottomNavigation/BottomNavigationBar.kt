package com.justcircleprod.hhcloneem.core.presentation.components.bottomNavigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.justcircleprod.hhcloneem.core.presentation.ui.theme.Grey2
import com.justcircleprod.hhcloneem.core.presentation.ui.theme.Grey4
import com.justcircleprod.hhcloneem.core.presentation.ui.theme.Red
import com.justcircleprod.hhcloneem.core.presentation.ui.theme.SFProDisplayFontFamily
import com.justcircleprod.hhcloneem.core.presentation.ui.theme.White

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationBarItems = listOf(
        BottomNavigationItem.Search,
        BottomNavigationItem.Favourite,
        BottomNavigationItem.Responses,
        BottomNavigationItem.Messages,
        BottomNavigationItem.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                            if (item.badgeCount != null) {
                                Badge(containerColor = Red) {
                                    Text(
                                        text = item.badgeCount.toString(),
                                        fontFamily = SFProDisplayFontFamily,
                                        fontSize = 8.sp,
                                        color = White
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(item.iconDrawableRes),
                            contentDescription = stringResource(item.titleStringRes),
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(item.titleStringRes),
                        fontFamily = SFProDisplayFontFamily,
                        maxLines = 1,
                        fontSize = 10.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = if (isSystemInDarkTheme()) Grey4 else Grey2,
                    unselectedTextColor = if (isSystemInDarkTheme()) Grey4 else Grey2
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