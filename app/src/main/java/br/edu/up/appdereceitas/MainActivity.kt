package br.edu.up.appdereceitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.edu.up.appdereceitas.dados.TasteBookDatabase.Companion.abrirDataBase
import br.edu.up.appdereceitas.dados.repository.categoria.LocalCategoriaRepository
import br.edu.up.appdereceitas.dados.repository.categoria.RemoteCategoriaRepository
import br.edu.up.appdereceitas.dados.repository.receitas.LocalReceitaRepository
import br.edu.up.appdereceitas.dados.repository.receitas.RemoteReceitaRepository
import br.edu.up.appdereceitas.ui.viewmodel.CategoriaViewModel
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

        val localCategoriaRepository = LocalCategoriaRepository(db.categoriaDao())
        val remoteCategoriaRepository = RemoteCategoriaRepository()

        val receitaViewModel: ReceitaViewModel
        val categoriaViewModel: CategoriaViewModel

        if (isLocal) {
            receitaViewModel = ReceitaViewModel(localReceitaRepository)
            categoriaViewModel = CategoriaViewModel(localCategoriaRepository)
        } else {
            receitaViewModel = ReceitaViewModel(remoteReceitaRepository)
            categoriaViewModel = CategoriaViewModel(remoteCategoriaRepository)
        }


        setContent {
            RecipeApp(receitaViewModel, categoriaViewModel)
        }
    }
}


