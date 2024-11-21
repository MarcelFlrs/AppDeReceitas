package br.edu.up.appdereceitas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.dados.repository.categoria.CategoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoriaViewModel (
    private val repository: CategoriaRepository,
) : ViewModel() {

    fun gravarCategoria(categoria: Categoria) {
        viewModelScope.launch {
            repository.gravarCategoria(categoria)
            _categorias.value += categoria
        }
    }

    suspend fun getCategoriaById(id: Int?): Categoria? {
        return repository.buscarCategoriaPorId(id)
    }

    fun getNomeCategoriaById(categoriaId: Int): String {
        return _categorias.value.find { it.id == categoriaId }?.nome ?: "Categoria não encontrada"
    }

    fun deleteCategoria(categoria: Categoria) {
        viewModelScope.launch {
            repository.deleteCategoria(categoria)
        }
    }

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categoria: StateFlow<List<Categoria>> get() = _categorias

    init {
        viewModelScope.launch {
            repository.listarCategorias().collectLatest { listaDeCategorias ->
                _categorias.value = listaDeCategorias
            }
        }
    }




}
