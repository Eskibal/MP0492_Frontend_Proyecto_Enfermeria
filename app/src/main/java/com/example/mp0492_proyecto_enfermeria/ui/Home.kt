package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mp0492_proyecto_enfermeria.R

final data class Nurse(
    val id: Int,
    val name: String,
    val age: Int,
    val speciality: String,
    val phone: String
)

@Composable
fun Home(modifier: Modifier = Modifier) {
    var screen by remember { mutableStateOf<Int>(4) }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (screen) {
                4 -> HomeScreen()
                3 -> NurseLoginScreen()
                2 -> NurseListScreen(sampleNurses)
                1 -> NurseSearchScreen()
            }
        }
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Button(onClick = { screen = 4 }) {
                    Text(text = "Home")
                }
                Button(onClick = { screen = 3 }) {
                    Text(text = "Login")
                }
            }
            Column {
                Button(onClick = { screen = 2 }) {
                    Text(text = "Show Nurses")
                }
                Button(onClick = { screen = 1 }) {
                    Text(text = "Search Nurse")
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.nurse_image),
            contentDescription = null,
            modifier.size(200.dp)
        )
        Row(modifier.padding(top = 300.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = "Nurse Application", style = MaterialTheme.typography.titleLarge)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(Modifier)
}