package br.edu.up.appdereceitas

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.appdereceitas.screen.ListaReceitas
import br.edu.up.appdereceitas.ui.model.CategoriaViewModel
import br.edu.up.appdereceitas.ui.screen.AdicionarReceitas
import br.edu.up.appdereceitas.ui.screen.DetalhesReceita
import br.edu.up.appdereceitas.ui.screen.TelaCategorias
import br.edu.up.appdereceitas.ui.theme.AppDeReceitasTheme
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel

@Preview(
    device = Devices.PIXEL
)
@Composable
fun RecipeApp() {
    AppDeReceitasTheme{
        val receitaViewModel: ReceitaViewModel = viewModel()
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "_receitas") {


            composable("categorias") { TelaCategorias(navController) }

            composable("_receitas") {
                ListaReceitas(
                    receitas = receitaViewModel.receitas,
                    onReceitaClick = { receita -> navController.navigate("detalhes/${receita.nome}") },
                    onCriarReceita = { navController.navigate("adicionar") },
                    navController = navController
                )
            }

            composable("detalhes/{receitaNome}") { backStackEntry ->
                val receitaNome = backStackEntry.arguments?.getString("receitaNome")
                val receita = receitaViewModel.receitas.find { it.nome == receitaNome }
                if (receita != null) {
                    DetalhesReceita(navController = navController, receita = receita, receitaViewModel)
                }
            }
            composable("_categorias") { TelaCategorias(navController) }


            composable("adicionar") {
                AdicionarReceitas(navController, onAdicionarReceita = { novaReceita ->
                    receitaViewModel.adicionarReceita(novaReceita)
                }, categoriaViewModel = CategoriaViewModel())
            }

        }
    }
}
