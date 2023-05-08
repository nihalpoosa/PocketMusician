package com.aimusic.pocketmusician.pages

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aimusic.pocketmusician.*
import com.aimusic.pocketmusician.R
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.*
import kotlin.math.roundToInt
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.random.Random

@ExperimentalMaterial3Api
@Composable
fun SongQueue(genreId: Int, subGenreId: Int, numOfSongs: Int, navController: NavController){
    var waitForTheNetworkCall by remember{ mutableStateOf(true) }
    var useDefaultBackground by remember{ mutableStateOf(false) }
    var backgroundImageUrl by remember{ mutableStateOf("") }
    if(waitForTheNetworkCall) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    else Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var subGenreName = GenreFactory.getSubGenreByIdAndOption(genreId, subGenreId)
        var songList = Array(numOfSongs) { i -> "$subGenreName song $i" }
        var rawSongList = RawSongList()
        rawSongList.initiateSongList()
        var genreToRawSongList = rawSongList.genreToSongList
        var selectedSongId by remember{ mutableStateOf(-10) }
        var someSongSelected by remember{ mutableStateOf(false) }
        var isPlaying by remember{ mutableStateOf(false) }
        var mediaPlayer = MediaPlayer()
        var mediaPlayerInitialized = false
        var context = LocalContext.current
        var positionInSong by remember{ mutableStateOf(0) }
        var durationOfSong by remember{ mutableStateOf(100) }
        lateinit var coroutineJob: Job
        val composableScope = rememberCoroutineScope()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Queue")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.inverseSurface,
                        titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            if(mediaPlayerInitialized) {
                                coroutineJob.cancel()
                                mediaPlayer.stop()
                                mediaPlayer.release()
                            }
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back to selection", tint = MaterialTheme.colorScheme.inverseOnSurface)
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if(mediaPlayerInitialized) {
                                coroutineJob.cancel()
                                mediaPlayer.stop()
                                mediaPlayer.release()
                            }
                            navController.navigate(Screen.UserPreferencesWithoutArgs.route+"/false")
                        }) {
                            Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.inverseOnSurface)
                        }
                        IconButton(onClick = {
                            FirebaseInstance.authentication.signOut()
                            if(mediaPlayerInitialized) {
                                coroutineJob.cancel()
                                mediaPlayer.stop()
                                mediaPlayer.release()
                            }
                            navController.navigate(Screen.LoginPage.route){
                                popUpTo(Screen.LoginPage.route){inclusive = true}
                            }
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "Logout", tint = MaterialTheme.colorScheme.inverseOnSurface)
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.15f)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp)
                    ){
                        Slider(
                            value = positionInSong.toFloat(),
                            onValueChange = {
                                coroutineJob.cancel()
                                positionInSong = it.roundToInt()
                            },
                            valueRange = 0f..durationOfSong.toFloat(),
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth(),
                            onValueChangeFinished = {
                                if(mediaPlayerInitialized){
                                    mediaPlayer.seekTo(positionInSong)
                                    coroutineJob = composableScope.launch {
                                        try{
                                            while(isPlaying){
                                                positionInSong = mediaPlayer.currentPosition
                                                delay(100)
                                            }
                                        } finally {
                                            coroutineContext.cancel()
                                        }
                                    }
                                }
                            }
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
                                        mediaPlayer.stop()
                                        mediaPlayer.release()
                                        mediaPlayer = MediaPlayer.create(context, genreToRawSongList[genreId]!![selectedSongId%12])
                                        positionInSong = 0
                                        durationOfSong = mediaPlayer.duration
                                        if(isPlaying) mediaPlayer.start()
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
                                    if (!isPlaying){
                                        mediaPlayer.pause()
                                    }
                                    else{
                                        mediaPlayer.start()
                                        coroutineJob = composableScope.launch {
                                            try{
                                                while(isPlaying){
                                                    positionInSong = mediaPlayer.currentPosition
                                                    delay(100)
                                                }
                                            } finally {
                                                coroutineContext.cancel()
                                            }
                                        }
                                    }
                                    if(!someSongSelected){
                                        selectedSongId = 0
                                        someSongSelected = true
                                        isPlaying = true
                                        if (mediaPlayerInitialized) {
                                            mediaPlayer.stop()
                                            mediaPlayer.release()
                                        }
                                        mediaPlayer = MediaPlayer.create(context, genreToRawSongList[genreId]!![selectedSongId%12])
                                        positionInSong = 0
                                        durationOfSong = mediaPlayer.duration
                                        mediaPlayer.start()
                                        mediaPlayerInitialized = true
                                        coroutineJob = composableScope.launch {
                                            try{
                                                while(isPlaying){
                                                    positionInSong = mediaPlayer.currentPosition
                                                    delay(100)
                                                }
                                            } finally {
                                                coroutineContext.cancel()
                                            }
                                        }
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
                                        mediaPlayer.stop()
                                        mediaPlayer.release()
                                        mediaPlayer = MediaPlayer.create(context, genreToRawSongList[genreId]!![selectedSongId%12])
                                        positionInSong = 0
                                        durationOfSong = mediaPlayer.duration
                                        if(isPlaying) mediaPlayer.start()
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
            Image(
                painter =
                    if(useDefaultBackground)
                        painterResource(R.drawable.default_background_image)
                    else rememberAsyncImagePainter(backgroundImageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            LazyColumn(contentPadding = values, modifier = Modifier.background(color = Color.Transparent)){
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
                            containerColor = listItemContainerColor.copy(alpha = 0.7f),
                            headlineColor = listItemHeadlineColor
                        ),
                        modifier = Modifier.clickable {
                            isPlaying = true
                            var justStarted = false
                            if (selectedSongId == -10) justStarted = true
                            selectedSongId = it
                            someSongSelected = true
                            if (mediaPlayerInitialized) {
                                mediaPlayer.stop()
                                mediaPlayer.release()
                            }
                            mediaPlayer = MediaPlayer.create(context, genreToRawSongList[genreId]!![selectedSongId%12])
                            positionInSong = 0
                            durationOfSong = mediaPlayer.duration
                            mediaPlayer.start()
                            mediaPlayerInitialized = true
                            if(justStarted){
                                coroutineJob = composableScope.launch {
                                    try{
                                        while(isPlaying){
                                            positionInSong = mediaPlayer.currentPosition
                                            delay(100)
                                        }
                                    } finally {
                                        coroutineContext.cancel()
                                    }
                                }
                            }
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

    LaunchedEffect(Unit){
        withContext(Dispatchers.IO) {
            try {
                val connection = URL(BackgroundImageSearchQuery.create(genreId, subGenreId)).openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"
                if (connection.responseCode == HttpsURLConnection.HTTP_OK) {
                    val jsonString = connection.inputStream.bufferedReader().use { it.readText() }
                    val apiResponse = Json { ignoreUnknownKeys = true }.decodeFromString<APIResponse>(jsonString)
                    backgroundImageUrl = apiResponse.results[Random.nextInt(0,10)].urls.regular
                } else {
                    throw Exception("HTTPS request failed")
                }
            }catch (e: Exception) {
                Log.i("exception", "${e.message}")
                useDefaultBackground = true
            } finally {
                waitForTheNetworkCall = false
            }
        }
    }
}
