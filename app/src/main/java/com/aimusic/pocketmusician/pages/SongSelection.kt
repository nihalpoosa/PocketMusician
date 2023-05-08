package com.aimusic.pocketmusician.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aimusic.pocketmusician.FirebaseInstance
import com.aimusic.pocketmusician.GenreFactory
import com.aimusic.pocketmusician.R
import com.aimusic.pocketmusician.Screen
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@ExperimentalMaterial3Api
@Composable
fun SongSelection(navController: NavController){
    var genrePreferencesInDatabase:List<Int> by remember{ mutableStateOf(listOf(3, 2, 1, 5, 6, 7)) }
    var songDurationInDatabase by remember{ mutableStateOf(5f) }
    var numOfSongsInDatabase by remember{ mutableStateOf(40f) }

    var waitForTheNetworkCall by remember{ mutableStateOf(true) }
    if(waitForTheNetworkCall) LinearProgressIndicator()
    else Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var genreIdList by remember { mutableStateOf(genrePreferencesInDatabase.joinToString(separator = ",")) }
        var songDuration by remember{ mutableStateOf(songDurationInDatabase) }
        var numOfSongs by remember{ mutableStateOf(numOfSongsInDatabase) }
        var genreId by remember{ mutableStateOf(-1) }
        var genreIdFromDialog by remember{ mutableStateOf(-1) }
        var dialogOptions by remember {mutableStateOf(listOf("Option 1", "Option 2", "Option 3", "Option 4"))}
        var dialogOptionIndex by remember { mutableStateOf(0) }
        var dialogOpen by remember { mutableStateOf(false) }
        var showAllGenres by remember{ mutableStateOf(false) }

        if (dialogOpen){
            AlertDialog(
                onDismissRequest = {
                    genreIdFromDialog = -1
                    dialogOpen = false
                },
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp)),
                content = {
                    Column {
                        Text(
                            text = "Select subgenre",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.titleLarge
                        )
                        dialogOptions.forEachIndexed { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { dialogOptionIndex = index },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = index == dialogOptionIndex,
                                    onClick = {dialogOptionIndex = index}
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = item)
                            }
                            Divider()
                        }
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                            Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                                Button(
                                    onClick = {
                                        genreIdFromDialog = -1
                                        dialogOpen = false
                                    }
                                ) {
                                    Text(text = "Cancel")
                                }
                            }
                            Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                                Button(
                                    onClick = {
                                        genreIdFromDialog = genreId
                                        dialogOpen = false
                                    }) {
                                    Text(text = "Confirm")
                                }
                            }
                        }
                    }
                }
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Music Types")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.inverseSurface,
                        titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Screen.UserPreferencesWithoutArgs.route+"/false")
                        }) {
                            Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.inverseOnSurface)
                        }
                        IconButton(onClick = {
                            FirebaseInstance.authentication.signOut()
                            navController.navigate(Screen.LoginPage.route){
                                popUpTo(Screen.LoginPage.route){inclusive = true}
                            }
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "Logout", tint = MaterialTheme.colorScheme.inverseOnSurface)
                        }
                    }
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        if(genreId != -1){
                            navController.navigate(Screen.SongQueueWithoutArgs.route+"/${genreId}/${dialogOptionIndex}/${numOfSongs.toInt()}")
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
                ) { if(genreIdList.isNotEmpty())
                    genreIdList.split(",").map{strId -> strId.toInt()}.map{curGenreId->
                        FilterChip(
                            selected = genreId == curGenreId && genreIdFromDialog == genreId,
                            onClick = {
                                genreId = curGenreId
                                dialogOptions = GenreFactory.getSubGenresById(curGenreId)
                                dialogOpen = true
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(GenreFactory.getGenreIcon(curGenreId)),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text(text = if(genreId == curGenreId && genreIdFromDialog == genreId){
                                        dialogOptions[dialogOptionIndex]
                                    } else {
                                        GenreFactory.getGenreById(curGenreId)
                                    }
                                )
                            }
                        )
                    }
                    FilterChip(
                        selected = false,
                        onClick = {
                            showAllGenres = !showAllGenres
                            genreIdList =
                                if(showAllGenres) GenreFactory.getAllGenreIds(if(genreIdList.isNotEmpty()) genreIdList.split(",").map{strId -> strId.toInt()} else listOf()).joinToString(separator = ",")
                                else genrePreferencesInDatabase.joinToString(separator = ",")
                        },
                        leadingIcon = {
                            Icon(
                                painter = if(!showAllGenres) painterResource(R.drawable.ic_expand)
                                    else painterResource(R.drawable.ic_collapse),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(text = if(showAllGenres) "less" else "more" )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = MaterialTheme.colorScheme.inverseSurface)

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

    LaunchedEffect(Unit){
        var userPreferencesInDataBaseQuery = FirebaseInstance.database.collection("users").whereEqualTo("email", FirebaseInstance.getUser()?.email)
        userPreferencesInDataBaseQuery.get()
            .addOnSuccessListener {results->
                for (result in results){
                    genrePreferencesInDatabase = result.data["preferences"] as List<Int>
                    songDurationInDatabase = (result.data["songDuration"] as Double).toFloat()
                    numOfSongsInDatabase = (result.data["numOfSongs"] as Double).toFloat()
                }
                waitForTheNetworkCall = false
            }
            .addOnFailureListener {
                waitForTheNetworkCall = false
            }
    }
}
