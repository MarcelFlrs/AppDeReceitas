package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.util.AppTopBar


@Composable
fun DetalhesReceita(navController: NavController) {

    Scaffold(
        topBar = {
            AppTopBar(
                title = { Text("Detalhes da Receita") },
                navController = navController
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF457304))
                    .padding(vertical = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Receitas",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
            Text(
                text = "Bolo de chocolate",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Descrição: Bolo de chocolate com um toque especial",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Ingredientes: Ingrediente 1\nIngrediente 2\nIngrediente 3\nIngrediente 4\nIngrediente 5\nIngrediente 6\nIngrediente 7\n",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo de Preparo: 40 min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo de Cozimento: 30 min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo de Descanso: 25 min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo Total: 95 min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


                ElevatedButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(vertical = 16.dp)
                    ){
                    Text("Apagar Receita")
                }
            }
        }
    }
}


