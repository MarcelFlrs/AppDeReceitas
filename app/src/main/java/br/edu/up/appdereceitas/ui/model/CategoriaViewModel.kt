package br.edu.up.appdereceitas.ui.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoriaViewModel : ViewModel() {
    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> get() = _categorias

    fun adicionarCategoria(novaCategoria: String) {
        if (novaCategoria.isNotEmpty()) {
            val atual = _categorias.value.toMutableList()
            atual.add(Categoria(novaCategoria))
            _categorias.value = atual
        }
    }
}



