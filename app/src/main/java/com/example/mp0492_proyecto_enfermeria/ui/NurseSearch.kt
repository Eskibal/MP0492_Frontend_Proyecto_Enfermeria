package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse

@Composable
fun NurseSearchScreen(allNurses: List<Nurse>) {

    var query by remember { mutableStateOf("") }
    var nurses by remember { mutableStateOf(listOf<Nurse>()) }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter nurse name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                nurses = searchNurses(query, allNurses)
            })
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { nurses = searchNurses(query, allNurses) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (nurses.isNotEmpty()) {
            Text(text = "Results:", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            nurses.forEach { nurse ->
                NurseCard(nurse)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

fun searchNurses(name: String, allNurses: List<Nurse>): List<Nurse> {
    if (name.isBlank()) return emptyList()
    return allNurses.filter { it.name.contains(name, ignoreCase = true) }
}

@Composable
fun NurseCard(nurse: Nurse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("ID: ${nurse.idNurse}", style = MaterialTheme.typography.titleMedium)
            Text("Nombre: ${nurse.name}")
            Text("Usuario: ${nurse.user}")
            Text("Email: ${nurse.email}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    NurseSearchScreen(emptyList())
}
