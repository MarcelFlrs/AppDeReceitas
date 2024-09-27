package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
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
import br.edu.up.appdereceitas.ui.util.AppTopBar
import br.edu.up.appdereceitas.ui.util.AppBottomBar

@Composable
fun ReceitasFavoritas(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(
                        text = "Receitas Favoritas",
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
                    text = "Receitas",
                    fontSize = 18.sp,
                    color = Color(0xFF75A902),
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(10) { index -> // Exemplo: 10 receitas favoritas
                    var favoritado by remember { mutableStateOf(true) } // Simulando que todas são favoritas

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ElevatedButton(
                            onClick = { navController.navigate("_detalhes") },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color(0xFFf1f1f1)
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
                                    text = "Bolo de chocolate $index", // Exemplo de título
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500
                                )
                                IconButton(onClick = { favoritado = !favoritado }) {
                                    Icon(
                                        imageVector = if (favoritado) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favoritar",
                                        tint = if (favoritado) Color(0xFFec7e30) else Color.Black
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
