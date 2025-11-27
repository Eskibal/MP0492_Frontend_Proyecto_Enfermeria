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

// Simple data class for nurse info

@Composable
fun NurseSearchScreen() {
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
                nurses = searchNurses(query)
            })
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { nurses = searchNurses(query) },
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

// Fake search function returning 3 sample results
fun searchNurses(name: String): List<Nurse> {
    if (name.isBlank()) return emptyList()

    return listOf(
        Nurse(id = 1, name = "${name} Johnson", age = 34, speciality = "Pediatrics", phone = "666 123 456"),
        Nurse(id = 2, name = "${name} Smith", age = 29, speciality = "Emergency Care", phone = "654 987 321"),
        Nurse(id = 3, name = "${name} Lee", age = 41, speciality = "Oncology", phone = "611 333 222")
    )
}

@Composable
fun NurseCard(nurse: Nurse) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${nurse.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Age: ${nurse.age}")
            Text(text = "Speciality: ${nurse.speciality}")
            Text(text = "Phone Number: ${nurse.phone}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NurseSearchPreview() {
    NurseSearchScreen()
}
