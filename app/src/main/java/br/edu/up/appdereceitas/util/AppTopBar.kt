package br.edu.up.appdereceitas.util

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    navController: NavController,
    onDrawerClick: (() -> Unit)? = null,
    mostrarBotaoVoltar: Boolean = false
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = when {
            showBackButton -> {
                {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            }
            onDrawerClick != null -> {
                {
                    IconButton(onClick = { onDrawerClick() }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Abrir Menu")
                    }
                }
            }
            else -> null // Sem ícone
        },
        modifier = Modifier.background(Color(0xFFFF6B1C))
    )
}

