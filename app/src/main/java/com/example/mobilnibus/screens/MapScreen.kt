package com.example.mobilnibus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilnibus.location.LocationService
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.firebase.auth.FirebaseAuth
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(auth: FirebaseAuth, navigateToSettings: () -> Unit) {

    val nis = LatLng(43.321445, 21.896104)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(nis, 15f)
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
                tiltGesturesEnabled = false,
                compassEnabled = false,
                rotationGesturesEnabled = false,
                myLocationButtonEnabled = false,
            )
        )
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL, isMyLocationEnabled = true))
    }

    Surface(color = Color.Black) {
        Surface(
            color = Color.White,
            modifier = Modifier.padding(0.dp, 72.dp, 0.dp, 72.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.85f),
                    cameraPositionState = cameraPositionState,
                    properties = properties,
                    uiSettings = uiSettings,
                    contentPadding = PaddingValues(8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = {
                                  cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(LocationService.latitude,LocationService.longitude),15f)
                                  },
                        modifier = Modifier.fillMaxHeight().padding(2.dp, 0.dp)
                    )
                    {
                        Text(text = "📌", fontSize = 32.sp)
                    }
                    ElevatedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxHeight().padding(2.dp, 0.dp)
                    )
                    {
                        Text(text = "🚏", fontSize = 32.sp)
                    }
                    ElevatedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxHeight().padding(2.dp, 0.dp)
                    )
                    {
                        Text(text = "🏅", fontSize = 32.sp)
                    }
                    ElevatedButton(
                        onClick = { navigateToSettings() },
                        modifier = Modifier.fillMaxHeight().padding(2.dp, 0.dp)
                    )
                    {
                        Text(text = "⚙️", fontSize = 32.sp)
                    }
                }
            }
        }
    }
}