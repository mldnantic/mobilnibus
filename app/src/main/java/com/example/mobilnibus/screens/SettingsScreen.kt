package com.example.mobilnibus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(auth: FirebaseAuth,navigateToStart:()->Unit) {
    Surface(color = Color.Black) {
        Surface(
            color = Color.White,
            modifier = Modifier.padding(0.dp, 24.dp, 0.dp, 0.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ElevatedButton(onClick = {
                    if (auth.currentUser != null) {
                        auth.signOut()
                        navigateToStart()
                    }
                })
                {
                    Text("Log out")
                }
            }
        }
    }
}