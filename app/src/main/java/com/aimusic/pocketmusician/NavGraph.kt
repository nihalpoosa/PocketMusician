package com.aimusic.pocketmusician

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aimusic.pocketmusician.pages.*

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
                navArgument("genreType") { type = NavType.StringType },
                navArgument("numOfSongs") { type = NavType.IntType }
            )
        ){
            SongQueue(it.arguments?.getInt("genreId")?: 1, it.arguments?.getString("genreType")?: "Default", it.arguments?.getInt("numOfSongs")?: 50, navController)
        }
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