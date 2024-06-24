package com.example.mobilnibus.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User (
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val username: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = ""
)