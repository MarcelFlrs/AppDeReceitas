package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.model.Receita
import br.edu.up.appdereceitas.util.AppTopBar


@Composable
fun DetalhesReceitas(receita: Receita, navController: NavController, onRemover: (Receita) -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar("Detalhes da Receita", navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text(text = receita.nome, style = MaterialTheme.typography.titleLarge)
            Text(text = "Ingredientes: ${receita.ingredientes}")
            Text(text = "Instruções: ${receita.instrucoes}")
            Button(onClick = {
                onRemover(receita)
                navController.popBackStack()
            }) {
                Text("Remover Receita")
            }
        }
    }
}

