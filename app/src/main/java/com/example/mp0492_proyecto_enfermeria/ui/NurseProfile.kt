package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mp0492_proyecto_enfermeria.R
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import androidx.compose.material3.*
import androidx.navigation.NavController

@Composable
fun NurseProfileScreen(viewModel: NurseViewModel, navController: NavController, nurseId: Int, modifier: Modifier = Modifier) {
    var nurse by remember { mutableStateOf<Nurse?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var editMode by remember { mutableStateOf(false) }

    if (editMode && nurse != null) {
        UpdateNurseScreen(viewModel, nurse!!) {
            editMode = false

            viewModel.loadNurseProfile(nurseId) {
                nurse = it
            }
        }
        return
    }

    LaunchedEffect(Unit) {
        val id = viewModel.loggedNurse?.idNurse

        if (id != null) {
            viewModel.loadNurseProfile(id) {
                nurse = it
            }
        } else {
            println("NO LOGGED NURSE")
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .fillMaxHeight(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(300.dp))
            Text("Hello, ${nurse?.name} ",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(10.dp))
            Text("(${nurse?.user})",
                style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(40.dp))
            Text("${nurse?.email}",
                style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .fillMaxHeight(),
            Arrangement.SpaceBetween,
            Alignment.Bottom
        ) {
            Column {
                Button(
                    onClick = { editMode = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.buttonColor)
                    )
                ) {
                    Text(stringResource(R.string.update))
                }

                Button(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.errorColor)
                    )
                ) {
                    Text(stringResource(R.string.delete))
                }
            }
        }
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },

                title = { Text("Delete Nurse") },

                text = { Text("¿Want to delete this profile?") },

                confirmButton = {
                    Button(onClick = {viewModel.loggedNurse?.idNurse?.let { id ->
                        viewModel.deleteNurse(id) { ok -> if (ok) {
                            viewModel.loggedNurse = null
                            navController.navigate("HomeScreen") {
                                popUpTo(0)
                            }
                        } }
                    }
                    }) {
                        Text("Yes")
                    }
                },

                dismissButton = {
                    Button(onClick = { showDeleteDialog = false }) {
                        Text("No")
                    }
                }
            )
        }
    }
}

@Composable
fun UpdateNurseScreen(viewModel: NurseViewModel, nurse: Nurse, onFinish: () -> Unit) {

    var name by remember { mutableStateOf(nurse.name) }
    var user by remember { mutableStateOf(nurse.user) }
    var email by remember { mutableStateOf(nurse.email) }
    var password by remember { mutableStateOf(nurse.password) }

    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Update Profile", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("User") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val updated = nurse.copy(
                    name = name,
                    user = user,
                    email = email,
                    password = password
                )

                viewModel.updateNurse(updated) { ok ->
                    message = if (ok) "Nurse updated successfully"
                    else "Failed to update"

                    if (ok) onFinish()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save changes")
        }

        Text(message)
    }
}
