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
fun SongSelection(navController: NavController){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var genreList = arrayOf("No selection", "Rock", "Dance", "Relaxing", "Study", "Electronic", "Suspenseful", "Workout", "Dubstep")
        var genreId by remember{ mutableStateOf(0) }
        var numOfSongs by remember{ mutableStateOf(40f) }
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        if(genreId != 0){
                            navController.navigate(Screen.SongQueueWithoutArgs.route+"/${genreId}/${genreList[genreId]}/${numOfSongs.toInt()}")
                        }
                    },
                    text = { Text(text = "Add Songs") },
                    icon = { Icon(Icons.Filled.Add, null) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }
        ) {it->
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
                        selected = genreList[genreId] == genreList[1],
                        onClick = {
                            if(genreList[genreId] == genreList[1]) genreId = 0
                            else genreId = 1
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
                        selected = genreList[genreId] == genreList[2],
                        onClick = {
                            if(genreList[genreId] == genreList[2]) genreId = 0
                            else genreId = 2
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
                        selected = genreList[genreId] == genreList[3],
                        onClick = {
                            if(genreList[genreId] == genreList[3]) genreId = 0
                            else genreId = 3
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
                        selected = genreList[genreId] == genreList[4],
                        onClick = {
                            if(genreList[genreId] == genreList[4]) genreId = 0
                            else genreId = 4
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
                        selected = genreList[genreId] == genreList[5],
                        onClick = {
                            if(genreList[genreId] == genreList[5]) genreId = 0
                            else genreId = 5
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
                        selected = genreList[genreId] == genreList[6],
                        onClick = {
                            if(genreList[genreId] == genreList[6]) genreId = 0
                            else genreId = 6
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
                        selected = genreList[genreId] == genreList[7],
                        onClick = {
                            if(genreList[genreId] == genreList[7]) genreId = 0
                            else genreId = 7
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
                        selected = genreList[genreId] == genreList[8],
                        onClick = {
                            if(genreList[genreId] == genreList[8]) genreId = 0
                            else genreId = 8
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
