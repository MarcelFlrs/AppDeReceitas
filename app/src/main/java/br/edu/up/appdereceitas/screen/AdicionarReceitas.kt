package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.model.Receita
import br.edu.up.appdereceitas.util.AppTopBar


@Composable
fun AdicionarReceitas(navController: NavController, onAdicionar: (Receita) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }
    var instrucoes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppTopBar("Adicionar Receita", navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
            TextField(value = ingredientes, onValueChange = { ingredientes = it }, label = { Text("Ingredientes") })
            TextField(value = instrucoes, onValueChange = { instrucoes = it }, label = { Text("Instruções") })
            Button(onClick = {
                onAdicionar(Receita(nome, ingredientes, instrucoes))
                navController.popBackStack()
            }) {
                Text("Adicionar Receita")
            }
        }
    }
}


