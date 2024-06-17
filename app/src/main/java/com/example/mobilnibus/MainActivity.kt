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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilnibus.ui.theme.MobilniBusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobilniBusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    MobilniBusApp()
                }
            }
        }
    }
}

@Composable
fun MobilniBusApp()
{
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = Screens.RegisterLogin.name)
        {
            composable(Screens.RegisterLogin.name)
            {
                RegisterLoginScreen(
                    onLogin = {
                        navController.navigate(Screens.Map.name)
                    },
                    onRegistracija =
                    {
                        navController.navigate(Screens.RegisterForm.name)
                    }
                )
            }
            composable(Screens.RegisterForm.name)
            {
                RegisterForm(onReg = {
                    navController.popBackStack(Screens.RegisterLogin.name,inclusive = false)
                })
            }
            composable(Screens.Map.name)
            {
                MapScreen()
            }
        }
    }
}

enum class Screens
{
    RegisterLogin,
    RegisterForm,
    Map
}