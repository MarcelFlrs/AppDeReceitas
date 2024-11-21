package br.edu.up.appdereceitas.dados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceitaDao {

    @Query("SELECT * FROM receitas")
    fun listarReceitas(): Flow<List<Receita>>

    @Delete
    suspend fun deleteReceita(receita: Receita)

    @Upsert
    suspend fun gravarReceita(receita: Receita)

    @Query("SELECT * FROM receitas WHERE id = :idx")
    suspend fun getReceitaById(idx: Int): Receita

    @Query("SELECT * FROM receitas WHERE categoriaId = :categoriaId")
    fun getReceitasByCategoriaId(categoriaId: Int?): Flow<List<Receita>>

    @Query("UPDATE receitas SET favoritado = :favoritado WHERE id = :idx")
    suspend fun atualizarFavorito(idx: Int, favoritado: Boolean)

    @Query("SELECT * FROM receitas WHERE favoritado = 1")
    fun listarFavoritas(): Flow<List<Receita>>


}
