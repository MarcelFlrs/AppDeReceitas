package br.edu.up.appdereceitas.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import br.edu.up.appdereceitas.ui.model.Categoria
import br.edu.up.appdereceitas.ui.model.Receita

class ReceitaViewModel : ViewModel() {

    private val _receitas = mutableStateListOf<Receita>()
    val receitas: List<Receita> get() = _receitas.toList()

    fun adicionarReceita(novaReceita: Receita) {
        viewModelScope.launch {
            _receitas.add(novaReceita)
        }
    }

    fun removerReceita(receita: Receita) {
        _receitas.remove(receita)

    }

}
