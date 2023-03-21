package com.ingentity.movie

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ingentity.movie.ui.home.HomePage

@Composable
fun NavigationBase() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomePage()
        }
        composable("friendslist") {

        }
    }
}