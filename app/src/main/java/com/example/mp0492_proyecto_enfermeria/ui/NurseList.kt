package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse

@Composable
fun NurseListScreen(nurses: List<Nurse>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(nurses) { nurse ->
            NurseListItem(nurse)
        }
    }
}

@Composable
fun NurseListItem(nurse: Nurse) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(nurse.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text("Usuario: ${nurse.user}")
            Text("Email: ${nurse.email}")
        }
    }
}
