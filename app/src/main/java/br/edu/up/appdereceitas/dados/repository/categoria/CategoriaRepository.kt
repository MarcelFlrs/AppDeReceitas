package br.edu.up.appdereceitas.dados.repository.categoria

import br.edu.up.appdereceitas.dados.model.Categoria
import kotlinx.coroutines.flow.Flow

interface CategoriaRepository {

    suspend fun listarCategorias(): Flow<List<Categoria>>

    suspend fun deleteCategoria(categoria: Categoria)

    suspend fun gravarCategoria(categoria: Categoria)

    suspend fun buscarCategoriaPorId(idx: Int): Categoria?

}