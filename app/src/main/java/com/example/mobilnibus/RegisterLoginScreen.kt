package com.example.mobilnibus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun RegisterLoginScreen(onKlik:()->Unit) {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Korisnicko ime", modifier = Modifier.align(Alignment.Start))
            Card(shape = MaterialTheme.shapes.large, modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "username",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displaySmall)
            }
            Text(text = "Sifra", modifier = Modifier.align(Alignment.Start))
            Card(shape = MaterialTheme.shapes.large, modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "********",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displaySmall)
            }
            Text(text = "Ime", modifier = Modifier.align(Alignment.Start))
            Card(shape = MaterialTheme.shapes.large, modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "first name",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displaySmall)
            }
            Text(text = "Prezime", modifier = Modifier.align(Alignment.Start))
            Card(shape = MaterialTheme.shapes.large, modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "last name",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displaySmall)
            }
            Text(text = "Telefon", modifier = Modifier.align(Alignment.Start))
            Card(shape = MaterialTheme.shapes.large, modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "phone",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displaySmall)
            }
            ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Registracija")
            }
            ElevatedButton(onClick = { onKlik() }, modifier = Modifier.fillMaxWidth())
            {
                Text(text = "Prijava")
            }
            Text(text = "Da bi ste mogli da koristite aplikaciju potrebno je da ste prijavljeni na Google nalog na vasem telefonu")

        }
    }
}
