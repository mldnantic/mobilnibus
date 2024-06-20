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
import com.example.mobilnibus.screens.RegisterForm
import com.example.mobilnibus.screens.RegisterLoginScreen
import com.example.mobilnibus.ui.theme.MobilniBusTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

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
    val formViewModel: FormViewModel = viewModel()
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
                    navController.popBackStack()
                },
                    viewModel = formViewModel)
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