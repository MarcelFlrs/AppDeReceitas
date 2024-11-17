package br.edu.up.appdereceitas.dados.repository.receitas

import br.edu.up.appdereceitas.dados.model.Receita
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await

class RemoteReceitaRepository : ReceitaRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val receitasCollection = firestore.collection("receitas")

    private val _receitasFlow = MutableStateFlow<List<Receita>>(emptyList())
    override fun listarReceita(): Flow<List<Receita>> = _receitasFlow

    override suspend fun addReceita(receita: Receita) {
        try {
            receitasCollection.add(receita).await()
        } catch (e: Exception) {
            throw Exception("Erro ao adicionar receita: ${e.message}")
        }
    }

    override suspend fun deleteReceita(receita: Receita) {
        try {
            val snapshot = receitasCollection
                .whereEqualTo("id", receita.id) // Certifique-se de que "id" está correto
                .get()
                .await()

            if (!snapshot.isEmpty) {
                snapshot.documents.first().reference.delete().await()
            } else {
                throw Exception("Receita não encontrada para exclusão.")
            }
        } catch (e: Exception) {
            throw Exception("Erro ao deletar receita: ${e.message}")
        }
    }

    override suspend fun updateReceita(receita: Receita) {
        try {
            val snapshot = receitasCollection
                .whereEqualTo("id", receita.id) // Certifique-se de que "id" está correto
                .get()
                .await()

            if (!snapshot.isEmpty) {
                snapshot.documents.first().reference.set(receita).await()
            } else {
                throw Exception("Receita não encontrada para atualização.")
            }
        } catch (e: Exception) {
            throw Exception("Erro ao atualizar receita: ${e.message}")
        }
    }

    init {
        // Escuta mudanças em tempo real e atualiza o fluxo
        receitasCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && !snapshot.isEmpty) {
                _receitasFlow.value = snapshot.documents.mapNotNull { it.toObject<Receita>() }
            }
        }
    }
}
