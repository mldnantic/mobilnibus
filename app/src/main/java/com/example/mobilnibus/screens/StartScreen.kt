package com.example.mobilnibus.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilnibus.MainActivity
import com.google.firebase.auth.FirebaseAuth

@Composable
fun StartScreen(auth: FirebaseAuth,mainActivity: MainActivity, formViewModel: FormViewModel,navigateToMap:()->Unit)
{
    fun createUserWithEmailAndPassword(auth: FirebaseAuth,mainActivity: MainActivity,email:String,password:String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(mainActivity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    formViewModel.reset()
                    navigateToMap()
                } else {
                    Toast.makeText(
                        mainActivity,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    Surface{
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(text = "MobilniBus",
                fontSize = 64.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(colors=listOf(Color.Magenta, Color.Cyan),)),
                fontFamily = FontFamily.Cursive)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = formViewModel.email,
                onValueChange = { formViewModel.email = it },
                label = { Text("Email") }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = formViewModel.password,
                onValueChange = { formViewModel.password = it },
                label = { Text("Password") }
            )
            ElevatedButton(
                onClick = {createUserWithEmailAndPassword(auth,mainActivity,formViewModel.email,formViewModel.password)},
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = "Registruj se")
            }
            Text(modifier = Modifier.padding(32.dp),
                text = "Da bi ste mogli da koristite aplikaciju potrebno je da ste prijavljeni na nalog",
                fontSize = 10.sp,
                color = Color.DarkGray)
        }
    }
}
