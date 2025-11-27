package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mp0492_proyecto_enfermeria.R
import androidx.compose.ui.res.colorResource

@Composable
fun NurseLoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val successText = stringResource(R.string.login_message_success)
    val errorText = stringResource(R.string.login_message_error)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(R.string.username)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    message = if (username.isNotBlank() && password.isNotBlank()) {
                        successText
                        // Ir a home
                    } else {
                        errorText
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.buttonColor)
                )
            ) {
                Text(
                    text = stringResource(R.string.login),
                    color = colorResource(R.color.textColor)
                )
            }
            Text(
                text = message,
                color = when (message) {
                    successText -> colorResource(R.color.successColor)
                    errorText -> colorResource(R.color.errorColor)
                    else -> colorResource(R.color.textColor)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNurseLogin() {
    NurseLoginScreen()
}
