package br.edu.up.appdereceitas.dados.repository.categoria

import br.edu.up.appdereceitas.dados.model.Categoria
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteCategoriaRepository : CategoriaRepository {

    private val db = FirebaseFirestore.getInstance()
    private val categoriaCollection = db.collection("categorias")

    override suspend fun listarCategorias(): Flow<List<Categoria>> = callbackFlow {
        val listener = categoriaCollection.addSnapshotListener { dados, erro ->
            if (erro != null) {
                close(erro)
                return@addSnapshotListener
            }
            if (dados != null) {
                val categorias = dados.documents.mapNotNull { doc ->
                    doc.toObject(Categoria::class.java)
                }
                trySend(categorias).isSuccess
            }
        }
        awaitClose { listener.remove() }
    }

    override suspend fun buscarCategoriaPorId(idx: Int?): Categoria? {
        if (idx == null) {
            return null
        }
        val documentSnapshot = categoriaCollection.document(idx.toString()).get().await()
        return if (documentSnapshot.exists()) {
            documentSnapshot.toObject(Categoria::class.java)
        } else {
            null
        }
    }

    private suspend fun getId(): Int{
        val dados = categoriaCollection.get().await()
        val maxId = dados.documents.mapNotNull {
            it.getLong("id")?.toInt()
        }.maxOrNull() ?: 0
        return maxId + 1
    }

    override suspend fun gravarCategoria(categoria: Categoria) {
        val document: DocumentReference
        if (categoria.id == 0 || categoria.id == null) {
            categoria.id = getId()
            document = categoriaCollection.document(categoria.id.toString())
        } else {
            document = categoriaCollection.document(categoria.id.toString())
        }

        document.set(categoria).await()
    }

    override suspend fun deleteCategoria(categoria: Categoria) {
        categoriaCollection.document(categoria.id.toString()).delete().await()
    }
}
