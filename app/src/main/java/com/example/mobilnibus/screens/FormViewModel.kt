package com.example.mobilnibus.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FormViewModel: ViewModel() {
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var ime: String by mutableStateOf("")
    var prezime: String by mutableStateOf("")
    var telefon: String by mutableStateOf("")
    fun reset()
    {
        email=""
        password=""
        ime=""
        prezime=""
        telefon=""
    }
}