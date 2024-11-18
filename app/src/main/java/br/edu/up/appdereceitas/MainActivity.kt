package br.edu.up.appdereceitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.edu.up.appdereceitas.dados.TasteBookDatabase.Companion.abrirDataBase
import br.edu.up.appdereceitas.dados.repository.receitas.LocalReceitaRepository
import br.edu.up.appdereceitas.dados.repository.receitas.RemoteReceitaRepository
import br.edu.up.appdereceitas.ui.viewmodel.ReceitaViewModel
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }
        val isLocal = false

        val db = abrirDataBase(this)

        val localReceitaRepository = LocalReceitaRepository(db.receitaDao())
        val remoteReceitaRepository = RemoteReceitaRepository()

        val receitaViewModel: ReceitaViewModel

        if (isLocal) {
            receitaViewModel = ReceitaViewModel(localReceitaRepository)
        } else {
            receitaViewModel = ReceitaViewModel(remoteReceitaRepository)
        }


        setContent {
            RecipeApp(receitaViewModel)
        }
    }
}


