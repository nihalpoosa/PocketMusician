package com.aimusic.pocketmusician

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aimusic.pocketmusician.pages.*

/*
    This file is responsible for the navigation of app to different activities based on the
    route string that is being specified. Defines all the screens in app as composables
 */
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.LoginPage.route){
        composable(route = Screen.SongSelection.route){
            SongSelection(navController)
        }
        composable(
            route = Screen.SongQueue.route,
            arguments = listOf(
                navArgument("genreId") { type = NavType.IntType },
                navArgument("subGenreId") { type = NavType.IntType },
                navArgument("numOfSongs") { type = NavType.IntType }
            )
        ){
            SongQueue(it.arguments?.getInt("genreId")?: 0, it.arguments?.getInt("subGenreId")?: 0, it.arguments?.getInt("numOfSongs")?: 50, navController)
        }
        // If the user is not logged in then login page is the home page
        // Else if the user is logged in then song selection page is the home page
        composable(route = Screen.LoginPage.route){
            if (FirebaseInstance.getUser() != null){
                SongSelection(navController)
            }
            else{
                LoginPage(navController)
            }
        }
        composable(route = Screen.RegisterPage.route){
            RegisterPage(navController)
        }
        composable(
            route = Screen.UserPreferences.route,
            arguments = listOf(
                navArgument("newUser") { type = NavType.BoolType },
            )
        ){
            UserPreferences(it.arguments?.getBoolean("newUser")?: false, navController)
        }
    }
}