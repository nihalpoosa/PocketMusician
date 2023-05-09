package com.aimusic.pocketmusician

import kotlin.random.Random

/*
    This class generates the url query based on the genre and subgenre the user has selected.
    This saves the api key and the url to hit to generate the random background image
    when the user selects a particular subgenre
 */
object BackgroundImageSearchQuery {
    val apiUrl = "https://api.unsplash.com/search/photos?page=PAGENUMBER&query=QUERY&client_id=qnJf7eJs9r_XHlMYqatKeoqphLmANRJnkIyK5JKdf_0"
    fun create(genreId: Int, subGenreId: Int): String{
        var urlQuery = ""

        when(genreId){
            0 -> urlQuery = apiUrl.replace("QUERY","rock music")
            1 -> when(subGenreId){
                0 -> urlQuery = apiUrl.replace("QUERY","party dance")
                1 -> urlQuery = apiUrl.replace("QUERY","samba dance")
                2 -> urlQuery = apiUrl.replace("QUERY","salsa dance")
                3 -> urlQuery = apiUrl.replace("QUERY","tap dance")
            }
            2 -> when(subGenreId){
                0 -> urlQuery = apiUrl.replace("QUERY","meditation")
                1 -> urlQuery = apiUrl.replace("QUERY","work")
                2 -> urlQuery = apiUrl.replace("QUERY","yoga")
            }
            3 -> when(subGenreId){
                0 -> urlQuery = apiUrl.replace("QUERY","study")
                1 -> urlQuery = apiUrl.replace("QUERY","coding")
                2 -> urlQuery = apiUrl.replace("QUERY","reading")
                3 -> urlQuery = apiUrl.replace("QUERY","writing")
            }
            4 -> when(subGenreId){
                0 -> urlQuery = apiUrl.replace("QUERY","edm")
                1 -> urlQuery = apiUrl.replace("QUERY","techno")
                2 -> urlQuery = apiUrl.replace("QUERY","synth")
                3 -> urlQuery = apiUrl.replace("QUERY","trance")
            }
            5 -> when(subGenreId){
                0 -> urlQuery = apiUrl.replace("QUERY","suspense thriller")
                1 -> urlQuery = apiUrl.replace("QUERY","orchestra")
                2 -> urlQuery = apiUrl.replace("QUERY","mysterious")
            }
            6 -> urlQuery = apiUrl.replace("QUERY","workout")
            7 -> urlQuery = apiUrl.replace("QUERY","music speaker")
            8 -> urlQuery = apiUrl.replace("QUERY","trumpet")
        }

        return urlQuery.replace("PAGENUMBER", Random.nextInt(1,4).toString())
    }
}