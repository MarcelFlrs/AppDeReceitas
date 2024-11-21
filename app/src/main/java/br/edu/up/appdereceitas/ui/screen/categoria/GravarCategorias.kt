package br.edu.up.appdereceitas.ui.screen.categoria

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.ui.screen.util.AppBottomBar
import br.edu.up.appdereceitas.ui.screen.util.AppTopBar
import br.edu.up.appdereceitas.ui.viewmodel.CategoriaViewModel

@Composable
fun GravarCategoria(
    navController: NavController,
    viewModelCategoria: CategoriaViewModel
) {
    var nomeCategoria by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(
                        text = "Gravar Categoria",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navController = navController
            )
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = nomeCategoria,
                onValueChange = { nomeCategoria = it },
                label = { Text("Nome da Categoria") },
                modifier = Modifier.fillMaxWidth(),
                isError = nomeCategoria.text.isBlank(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedButton(
                onClick = {
                    if (nomeCategoria.text.isNotBlank()) {
                        val categoria = Categoria(0, nomeCategoria.text)
                        viewModelCategoria.gravarCategoria(categoria)
                        navController.navigate("categorias")
                    } else {
                        Toast.makeText(navController.context, "Nome da categoria não pode ser vazio", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF75A902))
            ) {
                Text(
                    text = "Salvar Categoria",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}
