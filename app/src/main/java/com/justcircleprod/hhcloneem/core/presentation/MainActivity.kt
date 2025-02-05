package com.justcircleprod.hhcloneem.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.justcircleprod.hhcloneem.core.presentation.components.NavigationItem
import com.justcircleprod.hhcloneem.core.presentation.components.bottomNavigation.NavigationBottomBar
import com.justcircleprod.hhcloneem.core.presentation.theme.Black
import com.justcircleprod.hhcloneem.core.presentation.theme.HHCloneEMTheme
import com.justcircleprod.hhcloneem.core.presentation.theme.MyRippleConfiguration
import com.justcircleprod.hhcloneem.favouriteVacancies.presentation.FavouriteVacanciesScreen
import com.justcircleprod.hhcloneem.messages.presentation.MessagesScreen
import com.justcircleprod.hhcloneem.profile.presentation.ProfileScreen
import com.justcircleprod.hhcloneem.responses.presentation.ResponsesScreen
import com.justcircleprod.hhcloneem.search.presentation.SearchScreen
import com.justcircleprod.hhcloneem.vacancyDetails.presentation.VacancyDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Black.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Black.toArgb()),
        )

        setContent {
            HHCloneEMTheme {
                // RippleEffect
                CompositionLocalProvider(LocalRippleConfiguration provides MyRippleConfiguration) {
                    val navController = rememberNavController()
                    val mainViewModel = hiltViewModel<MainViewModel>()

                    val favouriteVacanciesCount by mainViewModel.favouriteVacanciesCount.collectAsStateWithLifecycle()

                    Scaffold(
                        bottomBar = {
                            NavigationBottomBar(navController, favouriteVacanciesCount)
                        },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        Surface {
                            NavHost(
                                navController,
                                startDestination = NavigationItem.Search.route,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                composable(NavigationItem.Search.route) {
                                    SearchScreen(navController)
                                }
                                composable(NavigationItem.FavouriteVacancies.route) {
                                    FavouriteVacanciesScreen(navController)
                                }
                                composable(NavigationItem.Responses.route) {
                                    ResponsesScreen()
                                }
                                composable(NavigationItem.Messages.route) {
                                    MessagesScreen()
                                }
                                composable(NavigationItem.Profile.route) {
                                    ProfileScreen()
                                }
                                composable(NavigationItem.VacancyDetails.route) {
                                    VacancyDetailScreen(navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}