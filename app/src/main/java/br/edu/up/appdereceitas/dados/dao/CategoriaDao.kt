package br.edu.up.appdereceitas.dados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import br.edu.up.appdereceitas.dados.model.Categoria
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoriaDao {

    @Query("SELECT * FROM categorias")
    fun listarCategorias(): Flow<List<Categoria>>

    @Delete
    suspend fun deleteCategoria(categoria: Categoria)

    @Upsert
    suspend fun gravarCategoria(categoria: Categoria)

    @Query("SELECT * FROM categorias WHERE id = :idx")
    suspend fun getCategoriaById(idx: Int): Categoria

}
