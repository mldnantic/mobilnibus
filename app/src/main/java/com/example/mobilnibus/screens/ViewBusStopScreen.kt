package com.example.mobilnibus.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilnibus.model.BusStopModel
import com.example.mobilnibus.viemodels.BusStopViewModel

@Composable
fun ViewBusStopScreen(busStopViewModel: BusStopViewModel,
                  navigateToMap: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        BusStopCard(busStop = busStopViewModel.busStop)
        Row {
            Button(modifier = Modifier.padding(12.dp),
                onClick = {
                    navigateToMap()
                }) {
                Text(text = "Back")
            }
            Button(modifier = Modifier.padding(12.dp),
                onClick = {
                    busStopViewModel.deleteBusStop(busStopViewModel.busStop.id)
                    navigateToMap()
                }) {
                Text(text = "Delete Bus stop")
            }
        }
    }
}

@Composable
fun BusStopCard(busStop: BusStopModel) {
    ElevatedCard(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()) {
        Column() {
            Text(
                modifier = Modifier.padding(6.dp),
                text = busStop.name,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = "Latitude:")
                Text(text = busStop.lat.toString())
            }
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = "Longitude:")
                Text(text = busStop.lng.toString())
            }
        }

    }
}