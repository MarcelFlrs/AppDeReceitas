package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.model.Receita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesReceitas(receita: Receita, navController: NavController, onRemover: (Receita) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Receita") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text(text = receita.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "Ingredientes: ${receita.ingredients}")
            Text(text = "Instruções: ${receita.instructions}")
            Button(onClick = {
                onRemover(receita)
                navController.popBackStack() // Volta para a tela anterior
            }) {
                Text("Remover Receita")
            }
        }
    }
}
