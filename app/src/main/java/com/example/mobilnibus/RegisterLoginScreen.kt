package com.example.mobilnibus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RegisterLoginScreen() {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Registracija")
            }
            ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Prijava")
            }
        }
    }
}