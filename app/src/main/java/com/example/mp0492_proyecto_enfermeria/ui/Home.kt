package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Home(modifier: Modifier = Modifier) {
    var screen by remember { mutableStateOf<Int>(0) }
    Column(modifier = modifier.fillMaxHeight(), Arrangement.Center) {
        when (screen) {
            4 -> Home()
            1 -> NurseSearchScreen()
        }
    }
    Row(modifier = modifier.fillMaxSize(), Arrangement.Center) {
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


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(Modifier)
}