package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import br.edu.up.appdereceitas.ui.util.AppTopBar
import br.edu.up.appdereceitas.ui.util.DrawerContent

@Composable
fun ListaReceitas() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()


    ModalNavigationDrawer(
        drawerContent = {},
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = {
                        Text(
                            text = "TasteBook",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = Color(0xFFec7e30),
                    onClick = { }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                item {
                    Button(
                        onClick = { navController.navigate("_receitas") },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Receita 1")
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}

