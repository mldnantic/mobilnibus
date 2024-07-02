package com.example.mobilnibus.viemodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mobilnibus.model.BusMarkerModel
import com.example.mobilnibus.model.BusStopModel
import com.example.mobilnibus.storage.BusMarkerStorageService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BusMarkerViewModel(private val storageService: BusMarkerStorageService): ViewModel() {


    val busMarkers: Flow<List<BusMarkerModel>> = storageService.busmarkers

    fun addBusMarker(busId:String,linija:String,lat:Double,lng:Double)
    {
        val b = BusMarkerModel(busId=busId,linija=linija,lat=lat,lng=lng)
        viewModelScope.launch {
            storageService.save(b)
        }
    }

    fun deleteBusMarker(id:String)
    {
        viewModelScope.launch {
            storageService.delete(id)
        }
    }

}

class BusMarkerViewModelFactory(private val storageService: BusMarkerStorageService): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass:Class<T>):T{
        if(modelClass.isAssignableFrom(BusStopViewModel::class.java)){
            return BusMarkerViewModel(storageService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}