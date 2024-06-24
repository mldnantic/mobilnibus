package com.example.mobilnibus

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.mobilnibus.screens.LocationService
import com.example.mobilnibus.screens.MapScreen
import com.example.mobilnibus.screens.SettingsScreen
import com.example.mobilnibus.screens.StartScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private fun startLocService()
    {
        Intent(this, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun stopLocService()
    {
        Intent(this, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            startService(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        this.requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )

        auth = Firebase.auth

        setContent {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    MobilniBusApp(auth, this,
                        svcStart = {
                            startLocService()
                        },
                        svcStop = {
                            stopLocService()
                        })
                }
        }
    }

    public override fun onStart() {
        enableEdgeToEdge()
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setContent {
                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background)
                    {
                        MobilniBusApp(auth, this,
                            svcStart = {
                                startLocService()

                            },
                            svcStop = {
                                stopLocService()
                            })
                    }
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("this.moveTaskToBack(true)"))
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        this.moveTaskToBack(true)
    }

}

@Composable
fun MobilniBusApp(auth: FirebaseAuth, mainActivity: MainActivity,svcStart:()->Unit,svcStop:()->Unit) {
    val navController = rememberNavController()
    val formViewModel: FormViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screens.StartScreen.name) {

        composable(Screens.StartScreen.name)
        {
            StartScreen(auth,mainActivity,formViewModel,
                startSvc = {svcStart()},
                navigateToMap = {navController.navigate(Screens.MapScreen.name)}
            )
        }

        composable(Screens.MapScreen.name)
        {
            MapScreen(auth,
                navigateToSettings = {navController.navigate(Screens.SettingsScreen.name)})
        }

        composable(Screens.SettingsScreen.name)
        {
            SettingsScreen(auth,
                navigateToStart = {
                    navController.popBackStack(Screens.StartScreen.name,inclusive = false)
                },
                startSvc = {svcStart()},
                stopSvc = {svcStop()})
        }
    }
}

enum class Screens{
    StartScreen,
    MapScreen,
    SettingsScreen
}