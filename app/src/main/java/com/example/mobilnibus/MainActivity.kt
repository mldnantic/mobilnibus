package com.example.mobilnibus

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mobilnibus.screens.FormViewModel
import com.example.mobilnibus.screens.MapScreen
import com.example.mobilnibus.ui.theme.MobilniBusTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
        enableEdgeToEdge()
        setContent {
            MobilniBusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                    {
                        MobilniBusApp(auth,this)
                    }
                }
            }
        }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setContent {
                MobilniBusTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    )
                    {
                        MapScreen(currentUser)
                    }
                }
            }
        }
    }
}

@Composable
fun MobilniBusApp(auth: FirebaseAuth, mainActivity: MainActivity) {
    val navController = rememberNavController()
    val formViewModel: FormViewModel = viewModel()
    fun createUserWithEmailAndPassword(email:String,password:String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(mainActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
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
                    brush = Brush.linearGradient(
                        colors=listOf(Color.Magenta, Color.Cyan),)),
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
                    onClick = {createUserWithEmailAndPassword(formViewModel.email,formViewModel.password)},
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

