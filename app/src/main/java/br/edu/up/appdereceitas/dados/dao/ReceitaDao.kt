package br.edu.up.appdereceitas.dados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceitaDao {

    @Query("SELECT * FROM receitas")
    fun listarReceitas(): Flow<List<Receita>>

    @Insert
    suspend fun insertReceita(receita: Receita)

    @Delete
    suspend fun deleteReceita(receita: Receita)

    @Update
    suspend fun updateReceita(receita: Receita)

    @Query("UPDATE receitas SET favoritado = :favoritado WHERE id = :id")
    suspend fun atualizarFavorito(id: Int, favoritado: Boolean)

    @Query("SELECT * FROM receitas WHERE favoritado = 1")
    fun listarFavoritas(): Flow<List<Receita>>
}
