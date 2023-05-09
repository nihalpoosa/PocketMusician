package com.aimusic.pocketmusician

/*
    This class is responsible for getting the raw file that is corresponding to the genre and
    subgenre user has selected. Every genre has 4 mp3 songs associated with it
 */
class RawSongList {
    var genreToSongList = HashMap<Int, Array<Int>>()
    fun initiateSongList(){
        genreToSongList[0] = arrayOf(R.raw.rock0, R.raw.rock1, R.raw.rock2, R.raw.rock3)
        genreToSongList[1] = arrayOf(R.raw.dance0, R.raw.dance1, R.raw.dance2, R.raw.dance3)
        genreToSongList[2] = arrayOf(R.raw.relaxing0, R.raw.relaxing1, R.raw.relaxing2, R.raw.relaxing3)
        genreToSongList[3] = arrayOf(R.raw.study0, R.raw.study1, R.raw.study2, R.raw.study3)
        genreToSongList[4] = arrayOf(R.raw.electronic0, R.raw.electronic1, R.raw.electronic2, R.raw.electronic3)
        genreToSongList[5] = arrayOf(R.raw.suspenseful0, R.raw.suspenseful1, R.raw.suspenseful2, R.raw.suspenseful3)
        genreToSongList[6] = arrayOf(R.raw.workout0, R.raw.workout1, R.raw.workout2, R.raw.workout3)
        genreToSongList[7] = arrayOf(R.raw.dubstep0, R.raw.dubstep1, R.raw.dubstep2, R.raw.dubstep3)
        genreToSongList[8] = arrayOf(R.raw.jazz0, R.raw.jazz1, R.raw.jazz2, R.raw.jazz3)
    }
}