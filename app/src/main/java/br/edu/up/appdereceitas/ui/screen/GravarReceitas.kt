package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.dados.model.Receita
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel
import androidx.compose.runtime.collectAsState


@Composable
fun GravarReceitas(
    navController: NavController,
    receitaId: Int?,
    viewModel: ReceitaViewModel
) {
    var titulo by remember { mutableStateOf(receita?.titulo ?: "") }
    var descricao by remember { mutableStateOf(receita?.descricao ?: "") }
    var ingredientes by remember { mutableStateOf(receita?.ingredientes ?: "") }
    var preparo by remember { mutableStateOf(receita?.preparo ?: "") }


    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Nome da Receita") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição da Receita") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = ingredientes,
            onValueChange = { ingredientes = it },
            label = { Text("Ingredientes da Receita") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = preparo,
            onValueChange = { preparo = it },
            label = { Text("Modo de Preparo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            val ingredientesLista = ingredientes.split(",").map { it.trim() }

            val novaReceita = Receita(
                id = receita?.id ?: 0,
                titulo = titulo,
                descricao = descricao,
                ingredientes = ingredientesLista,
                preparo = preparo
            )
            viewModel.gravarReceita(novaReceita)
            navController.popBackStack()
        }) {
            Text(if (receitaId == null) "Criar Receita" else "Salvar Alterações")
        }

    }
}


