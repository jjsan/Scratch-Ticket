package com.jjsan.scratchticket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjsan.scratchticket.screens.MainScreen
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.ACTIVATION_SCREEN
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.MAIN_SCREEN
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.SCRATCH_SCREEN
import com.jjsan.scratchticket.screens.ActivationScreen
import com.jjsan.scratchticket.screens.ScratchScreen

@Composable
fun ApplicationNavigation() {
    val navController = rememberNavController()
    val startNavigation = MAIN_SCREEN

    NavHost(navController = navController, startDestination = startNavigation) {

        composable(
            route = MAIN_SCREEN,
        ) {
            MainScreen()
        }

        composable(
            route = SCRATCH_SCREEN,
        ) {
            ScratchScreen()
        }

        composable(
            route = ACTIVATION_SCREEN,
        ) {
            ActivationScreen()
        }

    }

}

