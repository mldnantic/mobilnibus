package com.example.mobilnibus

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

@Composable
fun RegisterForm(onReg: () -> Boolean)
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
                Text(text = "Korisnicko ime")
                OutlinedTextField(value = "", onValueChange = {})
                Text(text = "Sifra")
                OutlinedTextField(value = "", onValueChange = {})
                Text(text = "Ime")
                OutlinedTextField(value = "", onValueChange = {})
                Text(text = "Prezime")
                OutlinedTextField(value = "", onValueChange = {})
                Text(text = "Telefon")
                OutlinedTextField(value = "", onValueChange = {})
            }
            ElevatedButton(onClick = { onReg() }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Registruj se")
            }
            Text(text = "Da bi ste mogli da koristite aplikaciju potrebno je da ste prijavljeni na Google nalog na vasem telefonu")
        }
    }
}