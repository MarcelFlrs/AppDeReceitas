package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.model.Categoria
import br.edu.up.appdereceitas.ui.util.AppTopBar

@Composable
fun TelaCategorias(navController: NavController) {
    var categorias by remember { mutableStateOf(mutableListOf<Categoria>()) }
    var novaCategoria by remember { mutableStateOf("") }

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

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(categorias) { categoria ->
                    Text(categoria.nome, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

