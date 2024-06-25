package com.example.mobilnibus.storage

import com.example.mobilnibus.model.BusStop
import com.example.mobilnibus.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class UserStorageService(private val firestore: FirebaseFirestore){

    val users: Flow<List<User>>
        get() =
            firestore
                .collection(USER_COLLECTION)
                .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                .dataObjects()

    suspend fun getPoi(userId: String): BusStop? =
        firestore.collection(USER_COLLECTION).document(userId).get().await().toObject()

    suspend fun save(user: User): String {
        val updatedUser = user.copy()
        return firestore.collection(USER_COLLECTION).add(updatedUser).await().id
    }

    suspend fun update(user: User): Void? =
        firestore.collection(USER_COLLECTION).document(user.id).set(user).await()

    suspend fun delete(userId: String) {
        firestore.collection(USER_COLLECTION).document(userId).delete().await()
    }

    companion object {
        private const val CREATED_AT_FIELD = "createdAt"
        private const val USER_COLLECTION = "users"
    }
}