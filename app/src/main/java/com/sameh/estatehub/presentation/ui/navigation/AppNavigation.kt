package com.sameh.estatehub.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sameh.estatehub.presentation.ui.screens.EstateDetailsScreen
import com.sameh.estatehub.presentation.ui.screens.EstateListingsScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ESTATE_LIST_SCREEN) {

        composable(
            "${Routes.ESTATE_DETAILS_SCREEN}/{${Routes.ESTATE_DETAILS_SCREEN_KEY}}",
            arguments = listOf(
                navArgument(Routes.ESTATE_DETAILS_SCREEN_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            EstateDetailsScreen()
        }

        composable(Routes.ESTATE_LIST_SCREEN) {
            EstateListingsScreen({ listingId ->
                navController.navigate("${Routes.ESTATE_DETAILS_SCREEN}/$listingId")
            })
        }
    }
}