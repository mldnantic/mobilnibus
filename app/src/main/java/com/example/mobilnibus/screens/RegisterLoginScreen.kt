package com.example.mobilnibus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun RegisterLoginScreen(onLogin: () -> Unit, onRegistracija: () -> Unit) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            
            Text(text = "MobilniBus", fontSize = 64.sp, style = TextStyle(
                brush = Brush.linearGradient(
                    colors=listOf(Color.Magenta, Color.Cyan)
                )
            ), fontFamily = FontFamily.Cursive)

            ElevatedButton(onClick = { onRegistracija() }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Registracija")
            }
            ElevatedButton(onClick = { onLogin() }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Prijava")
            }
            Text(text = "©Mladen Antic 2024")
        }
    }
}
