package com.aimusic.pocketmusician

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aimusic.pocketmusician.ui.theme.*

/*
    The main activity. This just displays the navigation controller,
    all the content and navigation happens in the NavGraph class
 */
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketMusicianTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}