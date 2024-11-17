package br.edu.up.appdereceitas.dados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.edu.up.appdereceitas.dados.model.Receita
import kotlinx.coroutines.flow.Flow


@Dao
interface ReceitaDao {

    @Query("SELECT * FROM receitas")
    fun listarReceitas(): Flow<List<Receita>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceita(receita: Receita)

    @Delete
    suspend fun deleteReceita(receita: Receita)

    @Update
    suspend fun updateReceita(receita: Receita)
}
