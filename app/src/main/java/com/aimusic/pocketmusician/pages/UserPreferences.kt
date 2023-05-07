package com.aimusic.pocketmusician.pages

import android.util.Log
import android.widget.Toast
import com.aimusic.pocketmusician.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
fun UserPreferences(newUser: Boolean, navController: NavController){
    var genrePreferencesInDatabase:List<Int> by remember{ mutableStateOf(listOf()) }
    var songDurationInDatabase by remember{ mutableStateOf(5f) }
    var numOfSongsInDatabase by remember{ mutableStateOf(40f) }

    var waitForTheNetworkCall by remember{ mutableStateOf(true) }
    if(waitForTheNetworkCall) CircularProgressIndicator()
    else Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var genreIdList = GenreFactory.getAllGenreIds(listOf())
        var genrePreferences by remember{ mutableStateOf(genrePreferencesInDatabase.joinToString(",")) }
        var songDuration by remember{ mutableStateOf(songDurationInDatabase) }
        var numOfSongs by remember{ mutableStateOf(numOfSongsInDatabase) }
        val context = LocalContext.current
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
                        val userEmail = FirebaseInstance.getUser()?.email
                        if(genrePreferences.isNotEmpty()){
                            if(newUser){
                                Log.i("New user", userEmail.toString())
                                FirebaseInstance.authentication.signOut()
                                FirebaseInstance.database
                                    .collection("users")
                                    .add(hashMapOf(
                                        "email" to userEmail,
                                        "preferences" to genrePreferences.split(","),
                                        "songDuration" to songDuration,
                                        "numOfSongs" to numOfSongs
                                    ))
                                    .addOnSuccessListener {docref->
                                        Toast.makeText(context, "User preferences saved", Toast.LENGTH_SHORT).show()
                                        navController.navigate(Screen.LoginPage.route){
                                            popUpTo(Screen.LoginPage.route){inclusive = true}
                                        }
                                    }
                                    .addOnFailureListener {ex->
                                        Toast.makeText(context, "Couldn't save user preferences", Toast.LENGTH_SHORT).show()
                                    }
                            }
                            else{
                                var userPreferencesInDataBaseQuery = FirebaseInstance.database.collection("users").whereEqualTo("email", userEmail)
                                userPreferencesInDataBaseQuery.get()
                                    .addOnSuccessListener {results->
                                        val batch = FirebaseInstance.database.batch()
                                        for (result in results){
                                            batch.update(result.reference, "preferences", genrePreferences.split(","))
                                            batch.update(result.reference, "songDuration", songDuration)
                                            batch.update(result.reference, "numOfSongs", numOfSongs)
                                        }
                                        batch.commit()
                                            .addOnSuccessListener {
                                                Toast.makeText(context, "User preferences saved", Toast.LENGTH_SHORT).show()
                                                navController.popBackStack()
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(context, "Couldn't update user preferences", Toast.LENGTH_SHORT).show()
                                            }
                                    }
                                    .addOnFailureListener {ex->
                                        Toast.makeText(context, "Couldn't get user preferences to update", Toast.LENGTH_SHORT).show()
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
                            selected = if(genrePreferences.isNotEmpty()) genrePreferences.split(",").map{strId -> strId.toInt()}.contains(curGenreId) else false,
                            onClick = {
                                genrePreferences =
                                    if (genrePreferences.isNotEmpty())
                                        if(genrePreferences.split(",").map{strId -> strId.toInt()}.contains(curGenreId))
                                            genrePreferences.split(",").map{strId -> strId.toInt()}.minus(curGenreId).joinToString(separator = ",")
                                        else
                                            genrePreferences.split(",").map{strId -> strId.toInt()}.plus(curGenreId).joinToString(separator = ",")
                                    else
                                        curGenreId.toString()
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
        if(!newUser){
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
        else{
            waitForTheNetworkCall = false
        }
    }
}
