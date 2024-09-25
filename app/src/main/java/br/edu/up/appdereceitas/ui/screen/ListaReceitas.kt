package br.edu.up.appdereceitas.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.model.Receita
import br.edu.up.appdereceitas.ui.util.AppTopBar
import br.edu.up.appdereceitas.ui.util.DrawerContent
import kotlinx.coroutines.launch

@Composable
fun ListaReceitas(
    receitas: List<Receita>,
    onReceitaClick: (Receita) -> Unit,
    onCriarReceita: () -> Unit,
    navController: NavController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerContent = { DrawerContent(navController) },
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
                    },
                    navController = navController,
                    onDrawerClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = Color(0xFFec7e30),
                    onClick = onCriarReceita
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar Receita")
                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                if (receitas.isEmpty()) {
                    item {
                        Text(
                            text = "Seja Bem vindo ao TasteBook, adicione uma receita para começar!.",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(receitas) { receita ->
                        Text(
                            text = receita.nome,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable { onReceitaClick(receita) }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

