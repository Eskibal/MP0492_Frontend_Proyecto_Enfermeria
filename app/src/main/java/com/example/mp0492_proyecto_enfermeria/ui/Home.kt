package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mp0492_proyecto_enfermeria.R
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(modifier: Modifier = Modifier) {
    val viewModel: NurseViewModel = viewModel()
    val navController = rememberNavController()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically

        ) {

        }

        NavHost(modifier = modifier.fillMaxSize(), navController = navController, startDestination = "HomeScreen") {
            composable(route = "HomeScreen") {
                HomeScreen(navController, message = R.string.welcome, loginButtonModifier = modifier.offset(y = -400.dp), homeButtonModifier = modifier.offset(y = -400.dp, x = -70.dp))
            }
            composable(route = "NurseLoginScreen") {
                NurseLoginScreen(viewModel, navController)
            }
            composable(route = "NurseRegisterScreen") {
                NurseRegisterScreen(viewModel, navController)
            }
            composable("LoggedHome") {
                LoggedHome(viewModel, navController)
                HomeScreen(navController, message = R.string.welcome2, loginButtonModifier = modifier.size(0.dp), homeButtonModifier = modifier.size(0.dp))
            }
            composable("NurseProfileScreen") {
                val id = viewModel.loggedNurse?.idNurse ?: 0

                LoggedHome(viewModel, navController)
                NurseProfileScreen(
                    viewModel = viewModel,
                    navController = navController,
                    nurseId = viewModel.loggedNurse?.idNurse ?: 0
                )
            }
            composable("NurseSearchScreen") {
                LoggedHome(viewModel, navController)
                NurseSearchScreen(viewModel)
            }
            composable("NurseListScreen") {
                LoggedHome(viewModel, navController)
                NurseListScreen(viewModel)
            }
        }
    }
}

@Composable
fun LoggedHome(viewModel: NurseViewModel, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .offset(y = -50.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Button(onClick = { navController.navigate("LoggedHome") },
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(R.color.buttonColor),
                containerColor = colorResource(R.color.alpha)
            ),
            modifier = modifier.offset(x = -20.dp)
        ) {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.titleLarge
            )
        }
            Button(onClick = { navController.navigate("NurseProfileScreen") }) { Text(stringResource(R.string.profile)) }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .offset(y = -50.dp),
            horizontalArrangement = Arrangement.Center,
            Alignment.CenterVertically
        ) {
            Button(onClick = { navController.navigate("NurseSearchScreen") }) { Text(stringResource(R.string.search)) }
            Spacer(Modifier.width(10.dp))
            Button(onClick = { navController.navigate("NurseListScreen") }) { Text(stringResource(R.string.nurses)) }
        }
    }
}
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier, message: Int, loginButtonModifier: Modifier, homeButtonModifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        Arrangement.End,
        Alignment.CenterVertically

    ) {
        Button(onClick = { navController.navigate("HomeScreen") },
        colors = ButtonDefaults.buttonColors(
            contentColor = colorResource(R.color.buttonColor),
            containerColor = colorResource(R.color.alpha)
        ),
        modifier = homeButtonModifier
    ) {
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.titleLarge
        )
    }
        Button(onClick = { navController.navigate("NurseLoginScreen") },
            modifier = loginButtonModifier
            ) { Text(stringResource(R.string.login)) }
    }
        Box(modifier.fillMaxSize().padding(top = 100.dp), contentAlignment = Alignment.Center, ) {
            Image(
                painter = painterResource(R.drawable.nurse_image),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Row(
                modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(stringResource(message), style = MaterialTheme.typography.displayMedium)
            }
        }
}

@Preview(showBackground = true)
@Composable
fun HomePreviewInternal() {
    Home(modifier = Modifier)
}
