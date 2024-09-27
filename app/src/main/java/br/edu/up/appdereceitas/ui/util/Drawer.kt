package br.edu.up.appdereceitas.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun DrawerContent(navController: NavController) {
    val drawerWidth = 0.5f
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(drawerWidth)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column {


            Text(
                text = "Menu",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Receitas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("_receitas")
                    }
            )

            Text(
                text = "Categorias",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("_categorias")
                    }
            )
        }
    }
}

