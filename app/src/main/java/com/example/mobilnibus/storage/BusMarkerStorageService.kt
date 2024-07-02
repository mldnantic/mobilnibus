package com.example.mobilnibus.storage

import com.example.mobilnibus.model.BusMarkerModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class BusMarkerStorageService(private val firestore: FirebaseFirestore){

    val busmarkers: Flow<List<BusMarkerModel>> get() = firestore
        .collection(BUSMARKERS_COLLECTION)
        .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
        .dataObjects()

    suspend fun save(busMarkerModel: BusMarkerModel): String {
        val updatedBusStop = busMarkerModel.copy()
        return firestore.collection(BUSMARKERS_COLLECTION).add(updatedBusStop).await().id
    }

    suspend fun delete(busId: String) {
        val id =firestore.collection(BUSMARKERS_COLLECTION).whereEqualTo("busId", busId)
            .get().await().first().id
        firestore.collection(BUSMARKERS_COLLECTION).document(id).delete().await()
    }

    companion object {
        private const val CREATED_AT_FIELD = "createdAt"
        private const val BUSMARKERS_COLLECTION = "busmarkers"
    }
}