package com.aimusic.pocketmusician

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.SongSelection.route){
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
    }
}