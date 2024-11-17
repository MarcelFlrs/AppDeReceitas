package br.edu.up.appdereceitas.dados.repository.receitas


import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow

interface ReceitaRepository {

    fun listarReceita(): Flow<List<Receita>>

    suspend fun addReceita(receita: Receita)

    suspend fun deleteReceita(receita: Receita)

    suspend fun updateReceita(receita: Receita)
}
