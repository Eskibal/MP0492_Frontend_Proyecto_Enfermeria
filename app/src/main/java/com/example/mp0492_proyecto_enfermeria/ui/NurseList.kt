package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mp0492_proyecto_enfermeria.R
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse

@Composable
fun NurseListScreen(viewModel: NurseViewModel) {

    // ✅ Llamada al backend al entrar
    LaunchedEffect(Unit) {
        viewModel.loadNursesFromBackend()
    }

    val uiState = viewModel.uiState.collectAsState().value
    val nurses = uiState.dynamicNurses

    if (nurses.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading nurses from backend...",
                style = MaterialTheme.typography.titleMedium
            )
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(
            items = nurses,
            key = { it.idNurse }
        ) { nurse ->
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ✅ Misma imagen para todos (placeholder local)
            val placeholderRes = R.drawable.nurse2

            if (nurse.imageUrl.isNotBlank()) {
                AsyncImage(
                    model = nurse.imageUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 16.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = placeholderRes),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 16.dp)
                )
            }

            Column {
                Text(
                    text = nurse.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text("${stringResource(R.string.user)}: ${nurse.user}")
                Text("${stringResource(R.string.email)}: ${nurse.email}")
            }
        }
    }
}
