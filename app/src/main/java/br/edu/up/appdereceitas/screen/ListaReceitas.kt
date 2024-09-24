package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.edu.up.appdereceitas.model.Receita

@Composable
fun ListaReceitas(receitas: List<Receita>, onReceitaClick: (Receita) -> Unit, onCriarReceita: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onCriarReceita) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(receitas) { receita ->
                Text(
                    text = receita.name,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onReceitaClick(receita) }
                )
                HorizontalDivider()
            }
        }
    }
}

