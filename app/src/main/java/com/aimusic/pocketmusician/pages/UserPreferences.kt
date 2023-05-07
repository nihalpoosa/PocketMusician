package com.aimusic.pocketmusician.pages

import com.aimusic.pocketmusician.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aimusic.pocketmusician.FirebaseInstance
import com.aimusic.pocketmusician.GenreFactory
import com.aimusic.pocketmusician.Screen
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@ExperimentalMaterial3Api
@Composable
fun UserPreferences(navController: NavController){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var genreIdList = GenreFactory.getAllGenreIds(listOf())
        var genrePreferences:List<Int> by remember{ mutableStateOf(listOf()) }
        var numOfSongs by remember{ mutableStateOf(40f) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "User Preferences")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.inverseSurface,
                        titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                    )
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        if(genrePreferences.isNotEmpty()){
                            
                            // onSavePreferences() from +"/${genreId}/${genreList[genreId]}/${numOfSongs.toInt()}"
                            if (FirebaseInstance.getUser() != null){
                                navController.popBackStack()
                            }
                            else{
                                navController.navigate(Screen.LoginPage.route){
                                    popUpTo(Screen.LoginPage.route){inclusive = true}
                                }
                            }
                        }
                    },
                    text = { Text(text = "Save Preferences") },
                    icon = { Icon(painter = painterResource(id = R.drawable.ic_save_preferences), null) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it)
                    .padding(20.dp)
            ){
                Text(
                    text = "Music Type",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisSpacing = 8.dp,
                    mainAxisSize = SizeMode.Wrap
                ) {
                    genreIdList.map {curGenreId->
                        FilterChip(
                            selected = genrePreferences.contains(curGenreId),
                            onClick = {
                                genrePreferences =
                                    if(genrePreferences.contains(curGenreId))
                                        genrePreferences.minus(curGenreId)
                                    else
                                        genrePreferences.plus(curGenreId)
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(GenreFactory.getGenreIcon(curGenreId)),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text(text = GenreFactory.getGenreById(curGenreId))
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = MaterialTheme.colorScheme.inverseSurface)

                var songDuration by remember{ mutableStateOf(5f) }
                Text(
                    text = "${songDuration.toInt()} minutes per song",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Slider(
                    value = songDuration,
                    onValueChange = {newVal -> songDuration = newVal },
                    valueRange = 3f..7f,
                    steps = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = MaterialTheme.colorScheme.inverseSurface)

                Text(
                    text = "${numOfSongs.toInt()} number of songs",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Slider(
                    value = numOfSongs,
                    onValueChange = {newVal -> numOfSongs = newVal },
                    valueRange = 1f..100f,
                    steps = 98
                )
            }
        }
    }
}
