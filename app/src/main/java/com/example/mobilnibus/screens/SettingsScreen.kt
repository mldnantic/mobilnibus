package com.example.mobilnibus.screens

import android.util.DisplayMetrics
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(auth: FirebaseAuth,navigateToStart:()->Unit,navigateBack:()->Unit,startSvc:()->Unit,stopSvc:()->Unit) {
    Surface(color = Color.Black) {
        Surface(
            color = Color.White,
            modifier = Modifier.padding(0.dp, 72.dp, 0.dp, 72.dp))
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween)
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically)
                {
                    ElevatedButton(
                        modifier = Modifier
                            .height(72.dp)
                            .width(72.dp),
                        onClick = {})
                    {
                        Text(text="🆔")
                    }
                    Column {
                        auth.currentUser?.displayName?.let{
                            Text(text = it, fontSize=24.sp, modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp))
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End)
                    {
                        ElevatedButton(
                            onClick =
                            {
                                stopSvc()
                                if (auth.currentUser != null) {
                                    auth.signOut()
                                    navigateToStart()
                                }
                            }
                        )
                        {
                            Text("Log out")
                        }
                    }
                }
                Column(verticalArrangement = Arrangement.Top)
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround)
                    {
                        ElevatedButton(onClick = {
                            startSvc()
                        })
                        {
                            Text("Track location")
                        }
                        ElevatedButton(onClick = {
                            stopSvc()
                        })
                        {
                            Text("Stop tracking")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround)
                    {
                        Slider(value = 0f, onValueChange = {},steps = 2, valueRange = 0f..500f)
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.End)
                {
                    ElevatedButton(
                        modifier = Modifier
                            .height(72.dp)
                            .width(72.dp),
                        onClick = {
                            navigateBack()
                        })
                    {
                        Text("◀️")
                    }
                }

            }
        }
    }
}