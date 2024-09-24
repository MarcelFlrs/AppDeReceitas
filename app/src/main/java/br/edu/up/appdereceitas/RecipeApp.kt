package br.edu.up.appdereceitas

import br.edu.up.appdereceitas.screen.ListaReceitas
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.appdereceitas.model.ReceitaViewModel
import br.edu.up.appdereceitas.screen.AdicionarReceitas
import br.edu.up.appdereceitas.screen.DetalhesReceitas

@Preview(
    device = Devices.PIXEL
)
@Composable
fun RecipeApp() {
    val receitaViewModel: ReceitaViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            ListaReceitas(receitas = receitaViewModel.receitas,
                onReceitaClick = { receita -> navController.navigate("detalhes/${receita.nome}") },
                onCriarReceita = { navController.navigate("adicionar") }
            )
        }

        composable("detalhes/{receitaName}") { backStackEntry ->
            val receitaName = backStackEntry.arguments?.getString("receitaName")
            val receita = receitaViewModel.receitas.find { it.nome == receitaName }
            if (receita != null) {
                DetalhesReceitas(receita, navController) { receitaRemovida ->
                    receitaViewModel.removerReceita(receitaRemovida)
                }
            }
        }
        composable("adicionar") {
            AdicionarReceitas(navController) { novaReceita ->
                receitaViewModel.adicionarReceita(novaReceita)
            }
        }
    }
}
