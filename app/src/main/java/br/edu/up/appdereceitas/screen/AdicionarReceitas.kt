package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.model.Receita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarReceitas(navController: NavController, onAdicionar: (Receita) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }
    var instrucoes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Receita") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            TextField(value = nome, onValueChange = { nome = it },label = { Text("Nome")})
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

