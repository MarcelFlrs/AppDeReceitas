package br.edu.up.appdereceitas

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.edu.up.appdereceitas.ui.screen.CategoriaReceitas
import br.edu.up.appdereceitas.ui.screen.ListaReceitas
import br.edu.up.appdereceitas.ui.screen.GravarReceitas
import br.edu.up.appdereceitas.ui.screen.DetalhesReceita
import br.edu.up.appdereceitas.ui.screen.ReceitasFavoritas
import br.edu.up.appdereceitas.ui.theme.AppDeReceitasTheme
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel



@Composable
fun RecipeApp(viewModelReceita: ReceitaViewModel) {
    AppDeReceitasTheme{
        val navController = rememberNavController()

        NavHost(
            navController = navController, startDestination = "_receitas",
        ) {

            composable("_categorias") {
                CategoriaReceitas(navController)
            }


            composable("_receitas") {backStackEntry ->
                val receitaId = backStackEntry.arguments?.getString("receitaId")?.toIntOrNull()
                ListaReceitas(navController, viewModelReceita, receitaId)
            }

            composable("_detalhes/{receitaId}") { backStackEntry ->
                val receitaId = backStackEntry.arguments?.getString("receitaId")?.toInt() ?: 0
                DetalhesReceita(
                    navController = navController,
                    viewModel = viewModelReceita,
                    receitaId = receitaId
                )
            }



            composable("_gravar_receitas/{receitaId}") { backStackEntry ->
                val receitaId = backStackEntry.arguments?.getString("receitaId")?.toIntOrNull()
                GravarReceitas(navController, receitaId, viewModelReceita)
            }



            composable ("_receitasfavoritas"){
                ReceitasFavoritas(navController, viewModelReceita)
            }

        }
    }
}
