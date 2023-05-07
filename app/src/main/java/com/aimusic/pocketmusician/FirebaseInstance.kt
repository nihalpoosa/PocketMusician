package com.aimusic.pocketmusician

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseInstance {
    val authentication = FirebaseAuth.getInstance()
    fun getUser() = Firebase.auth.currentUser
    val database = Firebase.firestore
}