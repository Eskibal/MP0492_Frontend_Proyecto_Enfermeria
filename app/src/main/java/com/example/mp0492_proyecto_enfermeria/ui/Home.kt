package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mp0492_proyecto_enfermeria.R
import com.example.mp0492_proyecto_enfermeria.ui.data.sampleNurses

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: NurseViewModel = viewModel()) {

    var screen by remember { mutableStateOf(1) }

    val dynamicNurses by viewModel.nurses.collectAsState()
    val allNurses = sampleNurses + dynamicNurses

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        ) {
            Text(
                text = "Nurse Application",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { screen = 1 }) { Text("Home") }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 2 }) { Text("Login") }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 4 }) { Text("Search") }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 3 }) { Text("Nurses") }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 5 }) { Text("Register") }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            when (screen) {
                1 -> HomeScreen()
                2 -> NurseLoginScreen(allNurses)
                3 -> NurseListScreen(allNurses)
                4 -> NurseSearchScreen(allNurses)
                5 -> NurseRegisterScreen(viewModel)
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
        Row(
            modifier.fillMaxSize().padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Welcome!", style = MaterialTheme.typography.displayMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreviewInternal() {
    Home(modifier = Modifier)
}
