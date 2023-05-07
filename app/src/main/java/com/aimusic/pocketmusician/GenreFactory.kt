package com.aimusic.pocketmusician

object GenreFactory {
    var genres = listOf("Rock", "Dance", "Relaxing", "Study", "Electronic", "Suspenseful", "Workout", "Dubstep", "Jazz")
    var genreIcons = listOf(R.drawable.ic_guitar, R.drawable.ic_dance, R.drawable.ic_relaxing, R.drawable.ic_study, R.drawable.ic_electronic, R.drawable.ic_suspenseful, R.drawable.ic_workout, R.drawable.ic_dubstep, R.drawable.ic_jazz)
    var subGenres: List<List<String>> = listOf(
        listOf("Metal", "Punk Rock", "Grunge", "Hard Rock", "Indie Rock"),
        listOf("Party", "Samba", "Salsa", "Tap Dance"),
        listOf("Meditation", "After Work", "Yoga"),
        listOf("Focus", "Coding", "Reading", "Writing"),
        listOf("EDM", "Techno", "Synthwave", "Trance"),
        listOf("Tension Building", "Crescendo", "Mysterious"),
        listOf("EDM", "House music", "South Indian", "Bollywood"),
        listOf("Glitch Hop", "Drum and Bass", "Deathstep"),
        listOf("Bebop", "Free Jazz")
    )
    public fun getGenreById(id: Int) = genres[id]
    public fun getSubGenresById(id: Int) = subGenres[id]

    public fun getSubGenreByIdAndOption(genreId: Int, subGenreId: Int) = subGenres[genreId][subGenreId]
    public fun getAllGenreIds(curGenreList: List<Int>): List<Int>{
        var allGenreList: List<Int> = curGenreList
        for(i in genres.indices){
            if(!allGenreList.contains(i)){
                allGenreList = allGenreList.plus(i)
            }
        }
        return allGenreList
    }
    public fun getGenreIcon(id: Int) = genreIcons[id]
}