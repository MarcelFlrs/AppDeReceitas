package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import br.edu.up.appdereceitas.ui.util.AppTopBar

@Composable
fun CategoriaReceitas() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar(
                title = { Text("Categorias") },
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
                Text(text = "Categoria 1", modifier = Modifier.padding(vertical = 4.dp))

        }
    }
}



