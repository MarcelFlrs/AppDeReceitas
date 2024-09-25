package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.model.Receita
import br.edu.up.appdereceitas.ui.util.AppTopBar
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel

@Composable
fun DetalhesReceita(
    navController: NavController,
    receita: Receita,
    receitaViewModel: ReceitaViewModel
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = { Text("Detalhes da Receita") },
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = receita.nome,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Descrição: ${receita.descricao}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Ingredientes: ${receita.ingredientes}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo de Preparo: ${receita.tempoPreparo}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo de Cozimento: ${receita.tempoCozimento}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo de Descanso: ${receita.tempoDescanso}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Tempo Total: ${receita.tempoTotal}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            ElevatedButton(
                onClick = {
                    receitaViewModel.removerReceita(receita)
                    navController.popBackStack()
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text("Apagar Receita")
        }
    }
    }
}


