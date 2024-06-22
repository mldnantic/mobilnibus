package com.example.mobilnibus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilnibus.screens.FormViewModel
import com.example.mobilnibus.screens.MapScreen
import com.example.mobilnibus.screens.StartScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
            {
                MobilniBusApp(auth, this, Screens.StartScreen)
            }
        }
    }

    public override fun onStart() {
        enableEdgeToEdge()
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setContent {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    MobilniBusApp(auth, this, Screens.MapScreen)
                }
            }
        }
    }

}

@Composable
fun MobilniBusApp(auth: FirebaseAuth, mainActivity: MainActivity,screen:Screens) {
    val navController = rememberNavController()
    val formViewModel: FormViewModel = viewModel()

    NavHost(navController = navController, startDestination = screen.name) {

        composable(Screens.StartScreen.name)
        {
            StartScreen(auth,mainActivity,formViewModel,
                navigateToMap = {navController.navigate(Screens.MapScreen.name)}
            )
        }

        composable(Screens.MapScreen.name)
        {
            MapScreen(currentUser = auth.currentUser)
        }

        //Settings
    }
}

enum class Screens{
    StartScreen,
    MapScreen,
    Settings
}