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
import androidx.compose.ui.res.stringResource

@Composable
fun Home(modifier: Modifier = Modifier) {
    val viewModel: NurseViewModel = viewModel()

    var screen by remember { mutableIntStateOf(1) }

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
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { screen = 1 }) { Text(stringResource(R.string.home)) }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 2 }) { Text(stringResource(R.string.login)) }
            Spacer(Modifier.width(10.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { screen = 4 }) { Text(stringResource(R.string.home)) }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 3 }) { Text(stringResource(R.string.nurses)) }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { screen = 5 }) { Text(stringResource(R.string.register)) }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            when (screen) {
                1 -> HomeScreen()
                2 -> NurseLoginScreen(viewModel)
                3 -> NurseListScreen(viewModel)
                4 -> NurseSearchScreen(viewModel)
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
