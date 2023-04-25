package com.aimusic.pocketmusician

import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aimusic.pocketmusician.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketMusicianTheme {
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
                                        setContent{SongQueue(genreList[genreId], numOfSongs.toInt())}
                                    }
                                },
                                text = {Text(text = "Add Songs")},
                                icon = {Icon(Icons.Filled.Add, null)},
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
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SongQueue(genreType: String, numOfSongs: Int){
    PocketMusicianTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            var songList = arrayOf("Naatu Naatu", "Despacito", "Real Slim Shady", "Gangta's Paradise", "Players", "Old Town Road","Naatu Naatu", "Despacito", "Real Slim Shady", "Gangta's Paradise", "Players", "Old Town Road","Naatu Naatu", "Despacito", "Real Slim Shady", "Gangta's Paradise", "Players", "Old Town Road","Naatu Naatu", "Despacito", "Real Slim Shady", "Gangta's Paradise", "Players", "Old Town Road","Naatu Naatu", "Despacito", "Real Slim Shady", "Gangta's Paradise", "Players", "Old Town Road")
            var songList = Array(numOfSongs) { i -> "$genreType song $i" }
            var selectedSongId by remember{ mutableStateOf(-1) }
            var someSongSelected by remember{ mutableStateOf(false) }
            var isPlaying by remember{ mutableStateOf(false) }
            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = {},
                        text = {Text(text = "Add songs")},
                        icon = {Icon(Icons.Filled.Add, null)},
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Queue")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.inverseSurface,
                            titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.15f)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp)
                        ){
                            var positionInSong by remember{ mutableStateOf(0f) }
                            Slider(
                                value = positionInSong,
                                onValueChange = {newPos -> positionInSong = newPos},
                                valueRange = 0f..100f,
                                modifier = Modifier.padding(0.dp).fillMaxWidth()
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp)
                            ){
                                IconButton(
                                    onClick = {
                                        if (someSongSelected){
                                            selectedSongId -= 1
                                            if (selectedSongId == -1) selectedSongId = songList.size-1
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_previous_filled_rounded),
                                        contentDescription = null
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        isPlaying = !isPlaying
                                        if(!someSongSelected){
                                            selectedSongId = 0
                                            someSongSelected = true
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = if(isPlaying) painterResource(id = R.drawable.ic_pause_filled_rounded) else painterResource(id = R.drawable.ic_play_filled_rounded),
                                        contentDescription = null
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        if (someSongSelected){
                                            selectedSongId += 1
                                            if (selectedSongId == songList.size) selectedSongId = 0
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_next_filled_rounded),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            ){values ->
                LazyColumn(contentPadding = values){
                    items(songList.size) {
                        var listItemContainerColor =
                            if (it == selectedSongId) MaterialTheme.colorScheme.surface
                            else MaterialTheme.colorScheme.surfaceVariant
                        var listItemHeadlineColor =
                            if (it == selectedSongId) MaterialTheme.colorScheme.onSurface
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        ListItem(
                            headlineText = { Text(songList[it]) },
                            colors = ListItemDefaults.colors(
                                containerColor = listItemContainerColor,
                                headlineColor = listItemHeadlineColor
                            ),
                            modifier = Modifier.clickable {
                                isPlaying = true
                                selectedSongId = it
                                someSongSelected = true
                            },
                            trailingContent = {
                                var isFavorite by remember{ mutableStateOf(false) }
                                IconButton(
                                    onClick = { isFavorite = !isFavorite }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = null,
                                        tint = if(isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        )
                        Divider(color = MaterialTheme.colorScheme.inverseSurface)
                    }
                }
            }
        }
    }
}


// Cards implementation
//                        LazyColumn(contentPadding = values){
//                            items(50) {
//                                Card(
//                                    modifier = Modifier.fillMaxWidth().padding(5.dp),
//                                    shape = MaterialTheme.shapes.small,
//                                    colors = CardDefaults.cardColors(
//                                        containerColor = MaterialTheme.colorScheme.surfaceVariant
//                                    )
//                                ){
//                                    Column(
//                                        modifier = Modifier.padding(5.dp)
//                                    ){
//                                        Text(
//                                            text = "Song $it"
//                                        )
//                                    }
//                                }
//                            }
//                        }