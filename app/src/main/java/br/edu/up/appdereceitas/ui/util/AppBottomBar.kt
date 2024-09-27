package br.edu.up.appdereceitas.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AppBottomBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFFec7e30),
        modifier = Modifier
            .height(56.dp)
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            BottomBarBotao(
                icon = Icons.Default.Home,
                label = "Receitas",
                onClick = { navController.navigate("_receitas") }
            )
            BottomBarBotao(
                icon = Icons.Default.Favorite,
                label = "Receitas Favoritas",
                onClick = { navController.navigate("_receitasfavoritas") }
            )

            BottomBarBotao(
                icon = Icons.Default.Star,
                label = "Categorias",
                onClick = { navController.navigate("_categorias") }
            )
        }

    }
}

@Composable
fun BottomBarBotao(icon: ImageVector, label: String, onClick: () -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}
