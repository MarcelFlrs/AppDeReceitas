package br.edu.up.appdereceitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.edu.up.appdereceitas.dados.TasteBookDatabase.Companion.abrirDataBase
import br.edu.up.appdereceitas.dados.repository.receitas.LocalReceitaRepository
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = abrirDataBase(this)

        val localReceitaRepository = LocalReceitaRepository(db.receitaDao())
        val receitaViewModel : ReceitaViewModel = ReceitaViewModel(localReceitaRepository)

        setContent {
            RecipeApp(receitaViewModel)
        }
    }
}


