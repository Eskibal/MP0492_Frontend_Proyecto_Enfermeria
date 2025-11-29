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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
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
    var screen by remember { mutableStateOf<Int>(1) }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 15.dp)) {
        Row(modifier
            .fillMaxWidth()
            .padding(start = 30.dp), Arrangement.Start) {
            Text(text = "Nurse Application", style = MaterialTheme.typography.titleLarge)
        }
        Row(modifier
            .fillMaxWidth()
            .padding(vertical = 35.dp), Arrangement.Center) {
            Button(onClick = { screen = 1 }) {
                Text(text = "Home")
            }
            Button(onClick = { screen = 2 }) {
                Text(text = "Login")
            }
            Button(onClick = { screen = 4 }) {
                Text(text = "Search")
            }
            Button(onClick = { screen = 3 }) {
                Text(text = "Nurses")
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (screen) {
                1 -> HomeScreen()
                2 -> NurseLoginScreen()
                3 -> NurseListScreen(sampleNurses)
                4 -> NurseSearchScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.nurse_image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Row(modifier.fillMaxSize().padding(top = 20.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = "Welcome!", style = MaterialTheme.typography.displayMedium)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(Modifier)
}