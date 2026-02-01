package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import com.example.mp0492_proyecto_enfermeria.R
import androidx.compose.ui.res.stringResource

@Composable
fun NurseSearchScreen(viewModel: NurseViewModel) {

    var query by remember { mutableStateOf("") }
    var nurses by remember { mutableStateOf(listOf<Nurse>()) }

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text(stringResource(R.string.search_hint)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                nurses = viewModel.searchNurses(query)
            })
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { nurses = viewModel.searchNurses(query) },
                    modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.search))
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (nurses.isNotEmpty()) {
            Text(stringResource(R.string.results), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            nurses.forEach { nurse ->
                NurseCard(nurse)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun NurseCard(nurse: Nurse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("ID: ${nurse.idNurse}", style = MaterialTheme.typography.titleMedium)
            Text("${stringResource(R.string.name)}: ${nurse.name}")
            Text("${stringResource(R.string.user)}: ${nurse.user}")
            Text("${stringResource(R.string.email)}: ${nurse.email}")
        }
    }
}
