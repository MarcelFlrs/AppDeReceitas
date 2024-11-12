package br.edu.up.appdereceitas.ui.screen.util

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: @Composable () -> Unit,
    navController: NavController,
    onDrawerClick: (() -> Unit)? = null,
    mostrarBotaoVoltar: Boolean = false
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            Row {
                if (mostrarBotaoVoltar) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
                if (onDrawerClick != null) {
                    IconButton(onClick = { onDrawerClick() }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Abrir Menu")
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFec7e30),
            titleContentColor = Color(0xFFf1f1f1),
            navigationIconContentColor = Color.Black
        )
    )


}
