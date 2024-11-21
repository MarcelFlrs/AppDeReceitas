package br.edu.up.appdereceitas.ui.screen.categoria

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.screen.util.AppBottomBar
import br.edu.up.appdereceitas.ui.screen.util.AppTopBar
import br.edu.up.appdereceitas.ui.viewmodel.CategoriaViewModel

@Composable
fun CategoriaReceitas(
    navController: NavController,
    viewModelCategoria: CategoriaViewModel
) {
    val categorias by viewModelCategoria.categoria.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(
                        text = "TasteBook",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navController = navController
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.White,
                contentColor = Color(0xFF75A902),
                onClick = {
                    navController.navigate("gravar_categoria")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Categoria")
            }
        },

        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .shadow(8.dp)
                    .graphicsLayer { shadowElevation = 8.dp.toPx() }
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Categorias",
                    fontSize = 18.sp,
                    color = Color(0xFF75A902),
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(categorias) { categoria ->

                    var showDialog by remember { mutableStateOf(false) }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Excluir Categoria") },
                            text = { Text("Tem certeza de que deseja excluir a categoria \"${categoria.nome}\"?") },
                            confirmButton = {
                                TextButton(onClick = {
                                    viewModelCategoria.deleteCategoria(categoria)
                                    showDialog = false
                                }) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }

                    Box(modifier = Modifier.fillMaxWidth()) {
                        ElevatedButton(
                            onClick = {},
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color(0xFFf1f1f1),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, bottom = 5.dp),
                            shape = RoundedCornerShape(2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.padding(20.dp),
                                    text = categoria.nome,
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500
                                )
                                IconButton(onClick = {

                                    navController.navigate("editar_categoria/${categoria.id}")

//                                Toast.makeText(
//                                    navController.context,
//                                    "Categoria inválida para edição",
//                                    Toast.LENGTH_SHORT
//                                ).show()

                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Create,
                                        contentDescription = "Editar",
                                        tint = Color.Black
                                    )

                                }

                                IconButton(onClick = {
                                    showDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Excluir",
                                        tint = Color.Red
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
