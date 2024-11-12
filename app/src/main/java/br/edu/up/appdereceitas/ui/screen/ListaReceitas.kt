package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun ListaReceitas(navController: NavController) {

    Scaffold(topBar = {
        AppTopBar(
            title = {
                Text(
                    text = "TasteBook", style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ), modifier = Modifier.fillMaxWidth()
                )
            }, navController = navController
        )
    },

        floatingActionButton = {
            FloatingActionButton(containerColor = Color(0xFF75A902),
                contentColor = Color.White,
                onClick = {
                    navController.navigate("_adicionarReceita")
                }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
            }
        },

        bottomBar = {
            AppBottomBar(navController = navController)
        }) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Box(modifier = Modifier
                .shadow(8.dp)
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                }
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Receitas",
                    fontSize = 18.sp,
                    color = Color(0xFF75A902),
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item {
                    var favoritado by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ElevatedButton(
                            onClick = { navController.navigate("_detalhes") },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color.White, contentColor = Color.White
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
                                    text = "Bolo de chocolate",
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500

                                )
                                IconButton(onClick = { favoritado = !favoritado }) {
                                    Icon(
                                        imageVector = if (favoritado) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favoritar",
                                        tint = if (favoritado) Color(0xFF75A902) else Color.Black
                                    )
                                }
                            }

                        }
                    }
                }

                item {
                    var favoritado by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ElevatedButton(
                            onClick = { navController.navigate("_detalhes") },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color.White, contentColor = Color.White
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
                                    text = "Bolo de chocolate 2",
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500

                                )
                                IconButton(onClick = { favoritado = !favoritado }) {
                                    Icon(
                                        imageVector = if (favoritado) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favoritar",
                                        tint = if (favoritado) Color(0xFF75A902) else Color.Black
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
