package br.edu.up.appdereceitas.ui.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoriaViewModel : ViewModel() {

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> get() = _categorias

    // Função para adicionar uma nova categoria
    fun adicionarCategoria(categoria: Categoria) {
        _categorias.value = _categorias.value + categoria
    }
}



