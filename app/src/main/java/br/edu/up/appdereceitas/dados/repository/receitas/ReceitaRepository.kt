package br.edu.up.appdereceitas.dados.repository.receitas

import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow

interface ReceitaRepository {

    fun listarReceita(): Flow<List<Receita>>

    fun listarFavoritas(): Flow<List<Receita>>

    suspend fun atualizarFavorito(id: Int, favoritado: Boolean)

    suspend fun gravarReceita(receita: Receita)

    suspend fun buscarReceitaPorId(idx: Int): Receita

    fun buscarReceitaPorCategoria(categoria: Categoria): Flow<List<Receita>>

    suspend fun deleteReceita(receita: Receita)


}
