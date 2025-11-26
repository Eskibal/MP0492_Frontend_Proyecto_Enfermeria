package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ------------------------------------------------------
// DATA CLASS
// ------------------------------------------------------
data class Nurse(
    val id: Int,
    val name: String,
    val specialty: String,
    val phone: String
)

// ------------------------------------------------------
// LISTA PRINCIPAL
// ------------------------------------------------------
@Composable
fun NurseListScreen(nurses: List<Nurse>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(nurses) { nurse ->
            NurseList(nurse)
        }
    }
}

// ------------------------------------------------------
// ITEM INDIVIDUAL (UNA TARJETA POR ENFERMERO)
// ------------------------------------------------------
@Composable
fun NurseList(nurse: Nurse) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = nurse.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Especialidad: ${nurse.specialty}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Teléfono: ${nurse.phone}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// ------------------------------------------------------
// PREVIEW PARA VERLO EN DEVICE
// ------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun PreviewNurseList() {

    val sampleNurses = listOf(
        Nurse(1, "Marc Lopez", "Urgencias", "666 123 456"),
        Nurse(2, "Jean Patrick", "Pediatría", "654 987 321"),
        Nurse(3, "Victor Liu", "UCI", "611 333 222"),
        Nurse(4, "Adam Serroukh", "Quirófano", "622 444 111")
        
    )

    NurseListScreen(nurses = sampleNurses)
}
