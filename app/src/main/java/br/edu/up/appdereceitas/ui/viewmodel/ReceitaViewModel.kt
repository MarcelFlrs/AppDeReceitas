package br.edu.up.appdereceitas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.appdereceitas.dados.model.Receita
import br.edu.up.appdereceitas.dados.repository.receitas.ReceitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class ReceitaViewModel (
    private val repository: ReceitaRepository
) : ViewModel() {

    fun addReceita(receita: Receita) {
        viewModelScope.launch {
            repository.addReceita(receita)
        }
    }

    fun deleteReceita(receita: Receita) {
        viewModelScope.launch {
            repository.deleteReceita(receita)
        }
    }

    fun updateReceita(receita: Receita) {
        viewModelScope.launch {
            repository.updateReceita(receita)
        }
    }

    private val _categorias = MutableStateFlow<List<Receita>>(emptyList())
    val receitas: StateFlow<List<Receita>> get() = _categorias

    init {
        viewModelScope.launch {
            repository.listarReceita().collectLatest { listaDeReceita ->
                _categorias.value = listaDeReceita
            }
        }
    }

}
