package com.justcircleprod.hhcloneem.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.justcircleprod.hhcloneem.core.presentation.components.NavigationItem
import com.justcircleprod.hhcloneem.core.presentation.components.bottomNavigation.BottomNavigationBar
import com.justcircleprod.hhcloneem.core.presentation.ui.theme.HHCloneEMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HHCloneEMTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Surface {
                        NavHost(
                            navController,
                            startDestination = NavigationItem.Search.route,
                            modifier = Modifier.fillMaxSize().padding(innerPadding)
                        ) {
                            composable(NavigationItem.Search.route) {

                            }
                            composable(NavigationItem.Favourite.route) {

                            }
                            composable(NavigationItem.Responses.route) {

                            }
                            composable(NavigationItem.Messages.route) {

                            }
                            composable(NavigationItem.Profile.route) {

                            }
                        }
                    }
                }
            }
        }
    }
}