package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.model.CategoriaViewModel
import br.edu.up.appdereceitas.ui.util.AppTopBar

@Composable
fun TelaCategorias(
    navController: NavController,
    categoriaViewModel: CategoriaViewModel
) {
    val categorias by categoriaViewModel.categorias.collectAsState()

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

            if (categorias.isEmpty()) {
                Text("Nenhuma categoria adicionada.")
            } else {
                categorias.forEach { categoria ->
                    Text(text = categoria.nome, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}



