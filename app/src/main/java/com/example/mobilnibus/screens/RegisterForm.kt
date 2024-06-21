package com.example.mobilnibus.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterForm(auth:FirebaseAuth, onReg: () -> Unit,viewModel:FormViewModel)
{
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            )
            {
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = {viewModel.email=it},
                    label = {Text("Korisnicko ime")}
                )
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = {viewModel.password=it},
                    label = {Text("Sifra")}
                )
                OutlinedTextField(
                    value = viewModel.ime,
                    onValueChange = {viewModel.ime=it},
                    label = {Text("Ime")}
                )
                OutlinedTextField(
                    value = viewModel.prezime,
                    onValueChange = {viewModel.prezime=it},
                    label = {Text("Prezime")}
                )
                OutlinedTextField(
                    value = viewModel.telefon,
                    onValueChange = {viewModel.telefon=it},
                    label = {Text("Telefon")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
            ElevatedButton(onClick = {
                onReg() },
                modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Registruj se")
            }
            Text(modifier = Modifier.padding(32.dp),
                text = "Da bi ste mogli da koristite aplikaciju potrebno je da ste prijavljeni na Google nalog na vasem telefonu",
                fontSize = 10.sp,
                color = Color.DarkGray)
        }
    }
}