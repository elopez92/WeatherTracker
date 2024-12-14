package us.elopez.weathertracker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import us.elopez.weathertracker.ui.home.HomeScreen

@Composable
fun WeatherNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
    }
}