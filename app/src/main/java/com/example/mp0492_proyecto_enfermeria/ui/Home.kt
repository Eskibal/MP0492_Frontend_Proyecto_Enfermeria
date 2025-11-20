package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello user!",
            modifier = modifier
        )
        Row {
            Button({ NurseLogin() }) {
                Text(text = "Login")
            }
            Button({ NurseList() }) {
                Text(text = "Show Nurses")
            }
            Button({ NurseSearch() }) {
                Text(text = "Search Nurse")
            }

        }
    }
}

@Composable
fun NurseLogin() {
    TODO("Not yet implemented")
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
     Home(Modifier)
}