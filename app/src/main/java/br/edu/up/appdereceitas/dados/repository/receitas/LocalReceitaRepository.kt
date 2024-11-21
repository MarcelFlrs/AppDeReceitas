package br.edu.up.appdereceitas.dados.repository.receitas

import br.edu.up.appdereceitas.dados.dao.ReceitaDao
import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow

class LocalReceitaRepository(
    private val receitaDao: ReceitaDao,
): ReceitaRepository {

    override fun listarReceita(): Flow<List<Receita>> = receitaDao.listarReceitas()

    override fun listarFavoritas(): Flow<List<Receita>> = receitaDao.listarFavoritas()

    override suspend fun atualizarFavorito(id: Int, favoritado: Boolean) {
        receitaDao.atualizarFavorito(id, favoritado)
    }

    override suspend fun gravarReceita(receita: Receita) {
        receitaDao.gravarReceita(receita)
    }

    override suspend fun buscarReceitaPorId(idx: Int): Receita {
        return receitaDao.getReceitaById(idx)
    }

    override fun buscarReceitaPorCategoria(categoria: Categoria): Flow<List<Receita>> {
        return receitaDao.getReceitasByCategoriaId(categoria.id)
    }

    override suspend fun deleteReceita(receita: Receita) {
        receitaDao.deleteReceita(receita)
    }


}
