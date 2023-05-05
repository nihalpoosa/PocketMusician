package com.aimusic.pocketmusician

sealed class Screen(val route: String){
    object SongQueueWithoutArgs: Screen("song_queue")
    object SongQueue: Screen("song_queue/{genreId}/{genreType}/{numOfSongs}")
    object SongSelection: Screen("song_selection")
}
