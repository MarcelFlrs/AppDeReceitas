package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.up.appdereceitas.model.Receita

@Preview(
    device = 
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaReceitas(
    receitas: List<Receita>,
    onReceitaClick: (Receita) -> Unit,
    onCriarReceita: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Receitas") },
                modifier = Modifier
                    .background(Color(0xFFFF6B1C))

            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCriarReceita) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            // Verifica se a lista de receitas está vazia
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



