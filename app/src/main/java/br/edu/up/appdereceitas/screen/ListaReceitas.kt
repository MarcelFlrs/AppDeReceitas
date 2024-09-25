package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.model.Receita
import br.edu.up.appdereceitas.util.AppTopBar


@Composable
fun ListaReceitas(
    receitas: List<Receita>,
    onReceitaClick: (Receita) -> Unit,
    onCriarReceita: () -> Unit,
    navController: NavController // Adicione este parâmetro
) {
    Scaffold(
        topBar = {
            AppTopBar("Receitas", navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCriarReceita) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            if (receitas.isEmpty()) {
                item {
                    Text(
                        text = "Não existem receitas adicionadas, adicione uma receita clicando no ícone abaixo.",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(receitas) { receita ->
                    Text(
                        text = receita.nome,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onReceitaClick(receita) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}





