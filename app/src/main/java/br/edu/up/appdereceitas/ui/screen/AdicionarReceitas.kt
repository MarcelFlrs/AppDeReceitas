package br.edu.up.appdereceitas.ui.screen



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

import br.edu.up.appdereceitas.ui.util.AppTopBar

@Composable
fun AdicionarReceitas() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar(
                title = { Text("Adicionar Receita") },
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TextField(
                value = "titulo",
                onValueChange = {},
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "descricao",
                onValueChange = { },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "ingredientes",
                onValueChange = {},
                label = { Text("Ingredientes") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: Arroz, Feijão") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "tempoPreparo",
                onValueChange = {},
                label = { Text("Tempo de Preparo") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 10 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "categoriaSelecionada",
                onValueChange = {},
                label = { Text("Categoria") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: Sobremesa") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "tempoCozimento",
                onValueChange = {},
                label = { Text("Tempo de Cozimento") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 20 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "tempoDescanso",
                onValueChange = {},
                label = { Text("Tempo de Descanso") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 15 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "tempoTotal",
                onValueChange = {},
                label = { Text("Tempo Total") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 45 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            ElevatedButton(
                onClick = {
                        navController.popBackStack()
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text("Salvar Receita")
            }
        }
    }
}

