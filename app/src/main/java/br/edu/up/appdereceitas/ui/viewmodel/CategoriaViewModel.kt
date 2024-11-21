package br.edu.up.appdereceitas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.dados.model.Receita
import br.edu.up.appdereceitas.dados.repository.categoria.CategoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoriaViewModel (
    private val repository: CategoriaRepository,
) : ViewModel() {

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias: StateFlow<List<Categoria>> = _categorias

    private val categoriasIniciais = listOf(
        Categoria(1, "Café da Manhã"),
        Categoria(2, "Prato principal"),
        Categoria(3, "Almoço"),
        Categoria(5, "Lanche"),
        Categoria(4, "Janta"),
        Categoria(6, "Bebida"),
    )

    fun buscarCategorias() {
        viewModelScope.launch {
            val categoriasObtidas = repository.listarCategorias()
            categoriasObtidas.collect { listaDeCategorias ->
                _categorias.value = listaDeCategorias
            }
        }
    }

    fun gravarCategoria(categoria: Categoria) {
        viewModelScope.launch {
            repository.gravarCategoria(categoria)
            _categorias.value += categoria
        }
    }

    private val _receita = MutableStateFlow<Receita?>(null)
    val receita: StateFlow<Receita?> get() = _receita

    suspend fun getReceitaById(id: Int): Categoria? {
        return repository.buscarCategoriaPorId(id)
    }

    fun deleteReceita(categoria: Categoria) {
        viewModelScope.launch {
            repository.deleteCategoria(categoria)
        }
    }



}
