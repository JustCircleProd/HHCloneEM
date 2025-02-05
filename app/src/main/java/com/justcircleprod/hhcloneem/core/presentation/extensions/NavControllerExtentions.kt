package com.justcircleprod.hhcloneem.core.presentation.extensions

import androidx.navigation.NavController

/** Helps avoid a crash caused by the user quickly tapping 2 (or more) times on a view that triggers an navigation action **/
fun NavController.navigateSafety(
    currentRoute: String?,
    targetRoute: String
) {
    if (this.currentDestination?.route == currentRoute) {
        this.navigate(targetRoute)
    }
}

/** Helps avoid a crash caused by the user quickly tapping 2 (or more) times on a view that triggers an navigation action **/
fun NavController.popBackStackSafety(currentRoute: String?) {
    if (this.currentDestination?.route == currentRoute) {
        this.popBackStack()
    }
}