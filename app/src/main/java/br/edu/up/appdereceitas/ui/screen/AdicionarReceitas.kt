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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.model.CategoriaViewModel
import br.edu.up.appdereceitas.ui.model.Receita
import br.edu.up.appdereceitas.ui.util.AppTopBar


@Composable
fun AdicionarReceitas(
    navController: NavController,
    onAdicionarReceita: (Receita) -> Unit,
    categoriaViewModel: CategoriaViewModel
) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("")  }
    var tempoPreparo by remember { mutableStateOf("") }
    var tempoCozimento by remember { mutableStateOf("") }
    var tempoDescanso by remember { mutableStateOf("") }
    var tempoTotal by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }

    val categorias by categoriaViewModel.categorias.collectAsState()

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
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = ingredientes,
                onValueChange = { ingredientes = it },
                label = { Text("Ingredientes") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: Arroz, Feijão") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = tempoPreparo,
                onValueChange = { tempoPreparo = it },
                label = { Text("Tempo de Preparo") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 10 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoria") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: Sobremesa") }
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = tempoCozimento,
                onValueChange = { tempoCozimento = it },
                label = { Text("Tempo de Cozimento") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 20 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = tempoDescanso,
                onValueChange = { tempoDescanso = it },
                label = { Text("Tempo de Descanso") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 15 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = tempoTotal,
                onValueChange = { tempoTotal = it },
                label = { Text("Tempo Total") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ex: 45 minutos") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            ElevatedButton(
                onClick = {

                    if (titulo.isNotEmpty() && descricao.isNotEmpty()) {
                        val novaReceita = Receita(
                            nome = titulo,
                            descricao = descricao,
                            ingredientes = ingredientes,
                            tempoPreparo = tempoPreparo,
                            tempoCozimento = tempoCozimento,
                            tempoDescanso = tempoDescanso,
                            tempoTotal = tempoTotal,
                            categoria = categoria
                        )
                        onAdicionarReceita(novaReceita)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text("Salvar Receita")
            }
        }
    }
}
