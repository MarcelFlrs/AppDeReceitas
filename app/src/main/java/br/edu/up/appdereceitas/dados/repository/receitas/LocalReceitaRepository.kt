package br.edu.up.appdereceitas.dados.repository.receitas

import br.edu.up.appdereceitas.dados.dao.ReceitaDao
import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow

class LocalReceitaRepository(
    private val receitaDao: ReceitaDao,
): ReceitaRepository {

    override fun listarReceita(): Flow<List<Receita>> = receitaDao.listarReceitas()

    override suspend fun addReceita(receita: Receita) {
        receitaDao.insertReceita(receita)
    }

    override suspend fun deleteReceita(receita: Receita) {
        receitaDao.deleteReceita(receita)
    }

    override suspend fun updateReceita(receita: Receita) {
        receitaDao.updateReceita(receita)
    }
}
