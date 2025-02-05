package com.justcircleprod.hhcloneem.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.justcircleprod.hhcloneem.core.presentation.components.NavigationItem
import com.justcircleprod.hhcloneem.core.presentation.components.bottomNavigation.NavigationBottomBar
import com.justcircleprod.hhcloneem.core.presentation.theme.HHCloneEMTheme
import com.justcircleprod.hhcloneem.favouriteVacancies.presentation.FavouriteVacanciesScreen
import com.justcircleprod.hhcloneem.messages.presentation.MessagesScreen
import com.justcircleprod.hhcloneem.profile.presentation.ProfileScreen
import com.justcircleprod.hhcloneem.responses.presentation.ResponsesScreen
import com.justcircleprod.hhcloneem.search.presentation.SearchScreen
import com.justcircleprod.hhcloneem.vacancyDetails.presentation.VacancyDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HHCloneEMTheme {
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
                                SearchScreen()
                            }
                            composable(NavigationItem.FavouriteVacancies.route) {
                                FavouriteVacanciesScreen()
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
                                VacancyDetailScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}