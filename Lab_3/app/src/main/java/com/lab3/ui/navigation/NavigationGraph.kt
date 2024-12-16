package com.lab3.ui.navigation

import PlaceDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lab3.ui.screens.placesList.PlacesListScreen

const val SCREEN_PLACE_LIST = "placeList"
const val SCREEN_PLACE_DETAILS = "placeDetails"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_PLACE_LIST
    ) {
        composable(
            route = SCREEN_PLACE_LIST,
        ) {
            PlacesListScreen(
                onDetailsScreen = { id ->
                    navController.navigate("$SCREEN_PLACE_DETAILS/$id")
                }
            )
        }

        composable(
            route = "$SCREEN_PLACE_DETAILS/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.Companion.IntType
                    nullable = false
                },
            )
        ) { backStack ->
            PlaceDetailsScreen(
                id = backStack.arguments?.getInt("id") ?: -1
            )
        }
    }

}