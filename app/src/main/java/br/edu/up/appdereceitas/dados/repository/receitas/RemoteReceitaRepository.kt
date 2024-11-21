package br.edu.up.appdereceitas.dados.repository.receitas

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import br.edu.up.appdereceitas.dados.model.Receita

class RemoteReceitaRepository: ReceitaRepository {

    private val db = FirebaseFirestore.getInstance()
    private val receitaCollection = db.collection("receitas")

    override fun listarReceita(): Flow<List<Receita>> = callbackFlow {
        val listener = receitaCollection.addSnapshotListener { dados, erro ->
            if (erro != null) {
                close(erro)
                return@addSnapshotListener
            }
            if (dados != null) {
                val receitas = dados.documents.mapNotNull { doc ->
                    doc.toObject(Receita::class.java)
                }
                trySend(receitas).isSuccess
            }
        }
        awaitClose { listener.remove() }
    }

    private suspend fun getId(): Int{
        val dados = receitaCollection.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarReceita(receita: Receita) {
        val document: DocumentReference
        if (receita.id == 0) {
            receita.id = getId()
            document = receitaCollection.document(receita.id.toString())
        } else {
            document = receitaCollection.document(receita.id.toString())
        }

        document.set(receita).await()
    }

    override suspend fun buscarReceitaPorId(idx: Int): Receita {
        val documentSnapshot = receitaCollection
            .whereEqualTo("id", idx)
            .get()
            .await()
            .documents
            .firstOrNull()

        return documentSnapshot?.toObject(Receita::class.java)
            ?: throw NoSuchElementException("Receita com id $idx não encontrada no Firestore")
    }

    override suspend fun deleteReceita(receita: Receita) {
        receitaCollection.document(receita.id.toString()).delete().await()
    }

     override suspend fun atualizarFavorito(id: Int, favoritado: Boolean) {
        val receitaRef = receitaCollection.document(id.toString())
        receitaRef.update("favoritado", favoritado).await()
    }

    override fun listarFavoritas(): Flow<List<Receita>> = callbackFlow {
        val listener = receitaCollection
            .whereEqualTo("favoritado", true)
            .addSnapshotListener { dados, erro ->
                if (erro != null) {
                    close(erro)
                    return@addSnapshotListener
                }
                if (dados != null) {
                    val receitasFavoritas = dados.documents.mapNotNull { doc ->
                        doc.toObject(Receita::class.java)
                    }
                    trySend(receitasFavoritas).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }
}
