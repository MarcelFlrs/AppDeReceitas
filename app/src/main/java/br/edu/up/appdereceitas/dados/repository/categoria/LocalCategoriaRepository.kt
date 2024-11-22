package br.edu.up.appdereceitas.dados.repository.categoria

import br.edu.up.appdereceitas.dados.dao.CategoriaDao
import br.edu.up.appdereceitas.dados.model.Categoria
import kotlinx.coroutines.flow.Flow

class LocalCategoriaRepository(
    private val categoriaDao: CategoriaDao
): CategoriaRepository {

    override suspend fun listarCategorias(): Flow<List<Categoria>> = categoriaDao.listarCategorias()

    override suspend fun gravarCategoria(categoria: Categoria) {
        categoriaDao.gravarCategoria(categoria)
    }

    override suspend fun deleteCategoria(categoria: Categoria) {
        categoriaDao.deleteCategoria(categoria)
    }

    override suspend fun buscarCategoriaPorId(idx: Int?): Categoria {
        return categoriaDao.getCategoriaById(idx)
    }


}
