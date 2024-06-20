package com.example.mobilnibus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    val nis = LatLng(43.321445, 21.896104)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(nis, 15f)
    }
    var uiSettings by remember { mutableStateOf(MapUiSettings(
        zoomControlsEnabled = false,
        tiltGesturesEnabled = false,
        compassEnabled = false,
        rotationGesturesEnabled = false)) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    Surface {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            GoogleMap(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.9f),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            )
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxHeight().padding(2.dp,0.dp))
                {
                    Text(text = "📌", fontSize = 32.sp)
                }
                ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxHeight().padding(2.dp,0.dp))
                {
                    Text(text = "🚏", fontSize = 32.sp)
                }
                ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxHeight().padding(2.dp,0.dp))
                {
                    Text(text = "🏅", fontSize = 32.sp)
                }
                ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxHeight().padding(2.dp,0.dp))
                {
                    Text(text = "⚙️", fontSize = 32.sp)
                }
            }
        }
    }

}