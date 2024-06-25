package com.example.mobilnibus.storage

import com.example.mobilnibus.model.BusStop
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class BusStopStorageService(private val firestore: FirebaseFirestore){

    val busStops: Flow<List<BusStop>>
        get() =
            firestore
                .collection(BUSSTOP_COLLECTION)
                .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                .dataObjects()

    suspend fun getPoi(busStopId: String): BusStop? =
        firestore.collection(BUSSTOP_COLLECTION).document(busStopId).get().await().toObject()

    suspend fun save(busStop: BusStop): String {
        val updatedBusStop = busStop.copy()
        return firestore.collection(BUSSTOP_COLLECTION).add(updatedBusStop).await().id
    }

    suspend fun update(busStop: BusStop): Void? =
        firestore.collection(BUSSTOP_COLLECTION).document(busStop.id).set(busStop).await()

    suspend fun delete(busStopId: String) {
        firestore.collection(BUSSTOP_COLLECTION).document(busStopId).delete().await()
    }

    companion object {
        private const val CREATED_AT_FIELD = "createdAt"
        private const val BUSSTOP_COLLECTION = "busstops"
    }
}