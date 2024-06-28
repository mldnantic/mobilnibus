package com.example.mobilnibus.storage

import com.example.mobilnibus.model.BusStopModel
import com.example.mobilnibus.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class UserStorageService(private val firestore: FirebaseFirestore){

    val users: Flow<List<UserModel>>
        get() =
            firestore
                .collection(USER_COLLECTION)
                .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                .dataObjects()

    suspend fun getUserUID(userId: String): BusStopModel? =
        firestore.collection(USER_COLLECTION).document(userId).get().await().toObject()

    suspend fun save(userModel: UserModel): String {
        val updatedUser = userModel.copy()
        return firestore.collection(USER_COLLECTION).add(updatedUser).await().id
    }

    suspend fun update(userModel: UserModel): Void? =
        firestore.collection(USER_COLLECTION).document(userModel.id).set(userModel).await()

    suspend fun delete(userId: String) {
        firestore.collection(USER_COLLECTION).document(userId).delete().await()
    }

    companion object {
        private const val CREATED_AT_FIELD = "createdAt"
        private const val USER_COLLECTION = "users"
    }
}