package br.edu.up.appdereceitas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.appdereceitas.dados.model.Receita
import br.edu.up.appdereceitas.dados.repository.receitas.ReceitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReceitaViewModel (
    private val repository: ReceitaRepository,
) : ViewModel() {

    fun gravarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.gravarReceita(receita)
        }
    }

    fun getReceitaById(id: Int): StateFlow<Receita?> = flow {
        val receita = repository.buscarReceitaPorId(id)
        emit(receita)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )


    fun deleteReceita(receita: Receita) {
        viewModelScope.launch {
            repository.deleteReceita(receita)
        }
    }

    fun atualizarFavorito(id: Int, favoritado: Boolean) {
        viewModelScope.launch {
            repository.atualizarFavorito(id, favoritado)
        }
    }

    private val _receitas = MutableStateFlow<List<Receita>>(emptyList())
    val receitas: StateFlow<List<Receita>> get() = _receitas

    private val _receitasFavoritas = MutableStateFlow<List<Receita>>(emptyList())
    val receitasFavoritas: StateFlow<List<Receita>> get() = _receitasFavoritas

    init {
        viewModelScope.launch {
            repository.listarReceita().collectLatest { listaDeReceitas ->
                _receitas.value = listaDeReceitas
            }
        }

        viewModelScope.launch {
            repository.listarFavoritas().collectLatest { listaDeFavoritas ->
                _receitasFavoritas.value = listaDeFavoritas
            }
        }
    }



}
