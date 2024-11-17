package br.edu.up.appdereceitas.dados.repository.receitas

import br.edu.up.appdereceitas.dados.dao.ReceitaDao
import br.edu.up.appdereceitas.dados.model.Receita
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow

class ReceitaRepository(
    private val receitaDao: ReceitaDao,
    private val firebaseDatabase: FirebaseDatabase
) {
    private val receitasRef = firebaseDatabase.getReference("receitas")

    fun listarReceita(): Flow<List<Receita>> = receitaDao.listarReceitas()

    suspend fun addReceita(receita: Receita) {
        receitaDao.insertReceita(receita)
        receitasRef.push().setValue(receita)
    }

    suspend fun deleteReceita(receita: Receita) {
        receitaDao.deleteReceita(receita)
        receitasRef.child(receita.id.toString()).removeValue()
    }

    suspend fun updateReceita(receita: Receita) {
        receitaDao.updateReceita(receita)
        receitasRef.child(receita.id.toString()).setValue(receita)
    }
}
