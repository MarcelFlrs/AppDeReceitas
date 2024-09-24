package br.edu.up.appdereceitas.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ReceitaViewModel : ViewModel() {
    private val _receitas = mutableStateListOf<Receita>()
    val receitas: List<Receita> get() = _receitas

    fun adicionarReceita(receita: Receita) {
        _receitas.add(receita)
    }

    fun removerReceita(receita: Receita) {
        _receitas.remove(receita)
    }
}
