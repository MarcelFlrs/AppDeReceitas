package br.edu.up.appdereceitas.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.appdereceitas.ui.util.AppTopBar
import br.edu.up.appdereceitas.ui.util.DrawerContent
import kotlinx.coroutines.launch

@Composable
fun CategoriaReceitas(navController: NavController) {

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
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navController = navController,
                    onDrawerClick = {
                        scope.launch { drawerState.open() }
                    })
            }
        ) { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Categorias",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    item {
                        ElevatedButton(
                            onClick = { navController.navigate("_detalhesCategoria")},
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(vertical = 16.dp),
                            shape = RoundedCornerShape(2.dp)
                        ) {
                            Text(
                                text = "Bolo de chocolate",
                                color = Color.Black,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}



