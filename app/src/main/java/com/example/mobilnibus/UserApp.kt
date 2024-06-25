package com.example.mobilnibus

import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserApp: Application() {

    val db by lazy { Firebase.firestore }

}