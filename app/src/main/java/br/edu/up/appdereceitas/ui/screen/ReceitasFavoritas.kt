package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel

@Composable
fun ReceitasFavoritas(navController: NavController, receitaViewModel: ReceitaViewModel) {
    val receitasFavoritas = receitaViewModel.receitasFavoritas.collectAsState()

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
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .shadow(8.dp)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                    }
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Receitas Favoritas",
                    fontSize = 18.sp,
                    color = Color(0xFF75A902),
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(receitasFavoritas.value) { receita ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ElevatedButton(
                            onClick = { navController.navigate("_detalhes/${receita.id}") },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            shape = RoundedCornerShape(2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.padding(20.dp),
                                    text = receita.titulo,
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500
                                )
                                IconButton(onClick = {
                                    val idReceita = receita.id
                                    if (idReceita != null) {
                                        receitaViewModel.atualizarFavorito(idReceita, !receita.favoritado)
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Desfavoritar",
                                        tint = Color(0xFF75A902)
                                    )
                                }
                            }
                        }
                    }
                }

                if (receitasFavoritas.value.isEmpty()) {
                    item {
                        Text(
                            text = "Nenhuma receita favoritada.",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
