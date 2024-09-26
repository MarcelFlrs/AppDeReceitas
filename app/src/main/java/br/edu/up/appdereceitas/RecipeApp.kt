package br.edu.up.appdereceitas

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.appdereceitas.screen.ListaReceitas
import br.edu.up.appdereceitas.ui.screen.AdicionarReceitas
import br.edu.up.appdereceitas.ui.screen.CategoriaReceitas
import br.edu.up.appdereceitas.ui.screen.DetalhesReceita
import br.edu.up.appdereceitas.ui.theme.AppDeReceitasTheme


@Preview(
    device = Devices.PIXEL
)
@Composable
fun RecipeApp() {
    AppDeReceitasTheme{
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "_receitas") {


            composable("_categorias") {
                CategoriaReceitas()
            }


            composable("_receitas") {
                ListaReceitas()
            }

            composable("detalhes/{receitaNome}") {
                DetalhesReceita()
            }


            composable("adicionar") {
                AdicionarReceitas()
            }

        }
    }
}
