package com.aimusic.pocketmusician.components

import com.aimusic.pocketmusician.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigator
import com.aimusic.pocketmusician.Screen
import com.aimusic.pocketmusician.ui.theme.PocketMusicianTheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@ExperimentalMaterial3Api
@Composable
fun RegistrationPreferences(navController: NavController){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var genrePreferences:List<Int> by remember{ mutableStateOf(listOf()) }
        var numOfSongs by remember{ mutableStateOf(40f) }
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        if(genrePreferences.isNotEmpty()){
                            // onSavePreferences() from +"/${genreId}/${genreList[genreId]}/${numOfSongs.toInt()}"
                            navController.navigate(Screen.LoginPage.route){
                                popUpTo(Screen.LoginPage.route){inclusive = true}
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
                    FilterChip(
                        selected = genrePreferences.contains(1),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(1))
                                    genrePreferences.minus(1)
                                else
                                    genrePreferences.plus(1)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_guitar),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Rock")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(2),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(2))
                                    genrePreferences.minus(2)
                                else
                                    genrePreferences.plus(2)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_dance),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Dance")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(3),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(3))
                                    genrePreferences.minus(3)
                                else
                                    genrePreferences.plus(3)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_relaxing),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Relaxing")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(4),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(4))
                                    genrePreferences.minus(4)
                                else
                                    genrePreferences.plus(4)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_study),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Study")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(5),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(5))
                                    genrePreferences.minus(5)
                                else
                                    genrePreferences.plus(5)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_electronic),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Electronic")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(6),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(6))
                                    genrePreferences.minus(6)
                                else
                                    genrePreferences.plus(6)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_suspenseful),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Suspenseful")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(7),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(7))
                                    genrePreferences.minus(7)
                                else
                                    genrePreferences.plus(7)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_workout),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Workout")
                        }
                    )
                    FilterChip(
                        selected = genrePreferences.contains(8),
                        onClick = {
                            genrePreferences =
                                if(genrePreferences.contains(8))
                                    genrePreferences.minus(8)
                                else
                                    genrePreferences.plus(8)
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_dubstep),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = "Dubstep")
                        }
                    )
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
