package com.aimusic.pocketmusician

/*
    This is a case class that used to get the screen route instead of defining these variables
    as raw strings and to make the code more readable
 */
sealed class Screen(val route: String){
    object SongQueueWithoutArgs: Screen("song_queue")
    object SongQueue: Screen("song_queue/{genreId}/{subGenreId}/{numOfSongs}")
    object SongSelection: Screen("song_selection")
    object LoginPage: Screen("login_page")
    object RegisterPage: Screen("register_page")
    object UserPreferencesWithoutArgs: Screen("user_preferences")
    object UserPreferences: Screen("user_preferences/{newUser}")

}
