package com.aimusic.pocketmusician

sealed class Screen(val route: String){
    object SongQueueWithoutArgs: Screen("song_queue")
    object SongQueue: Screen("song_queue/{genreId}/{genreType}/{numOfSongs}")
    object SongSelection: Screen("song_selection")
    object LoginPage: Screen("login_page")
    object RegisterPage: Screen("register_page")
    object UserPreferencesWithoutArgs: Screen("user_preferences")
    object UserPreferences: Screen("user_preferences/{newUser}")

}
