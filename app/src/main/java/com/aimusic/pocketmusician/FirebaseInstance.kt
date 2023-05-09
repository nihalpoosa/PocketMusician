package com.aimusic.pocketmusician

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/*
    This class is where the firebase instance is initialized and used everywhere so that
    we don't need to initialize firebase everytime we use it.
    This class holds both the authentication and database instances
 */
object FirebaseInstance {
    val authentication = FirebaseAuth.getInstance()
    fun getUser() = Firebase.auth.currentUser
    val database = Firebase.firestore
}