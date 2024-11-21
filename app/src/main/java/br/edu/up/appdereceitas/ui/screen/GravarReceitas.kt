import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.dados.model.Receita
import br.edu.up.appdereceitas.ui.viewmodel.CategoriaViewModel
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GravarReceitas(
    navController: NavController,
    receitaId: Int?,
    viewModelReceita: ReceitaViewModel,
    categorias: List<Categoria>
) {

    var receita by remember { mutableStateOf<Receita?>(null) }

    LaunchedEffect(receitaId) {
        receita = if (receitaId != null) {
            viewModelReceita.getReceitaById(receitaId)
        } else {
            null
        }
    }

    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }
    var preparo by remember { mutableStateOf("") }
    var categoriaId by remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    var selectedCategoria by remember { mutableStateOf("") }

    LaunchedEffect(receita) {
        if (receita != null) {
            titulo = receita!!.titulo
            descricao = receita!!.descricao
            ingredientes = receita!!.ingredientes.joinToString(", ")
            preparo = receita!!.preparo
            categoriaId = receita!!.categoriaId ?: 0
            selectedCategoria = categorias.find { it.id == categoriaId }?.nome ?: "Selecione a categoria"
        }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedCategoria,
                onValueChange = {},
                label = { Text("Categoria") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria.nome) },
                        onClick = {
                            categoriaId = categoria.id!!
                            selectedCategoria = categoria.nome
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
            label = { Text("Ingredientes da Receita (Separados por vírgula)") },
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
                preparo = preparo,
                favoritado = receita?.favoritado ?: false,
                categoriaId = categoriaId
            )
            viewModelReceita.gravarReceita(novaReceita)
            navController.popBackStack()
        }) {
            Text(if (receitaId == null) "Criar Receita" else "Salvar Alterações")
        }
    }
}
