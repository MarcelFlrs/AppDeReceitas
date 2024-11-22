package br.edu.up.appdereceitas.dados.repository.categoria

import br.edu.up.appdereceitas.dados.model.Categoria
import br.edu.up.appdereceitas.dados.model.Receita
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RemoteCategoriaRepository : CategoriaRepository {

    private val db = FirebaseFirestore.getInstance()
    private val categoriaCollection = db.collection("categorias")

    override suspend fun listarCategorias(): Flow<List<Categoria>> = flow {
        try {
            val querySnapshot = categoriaCollection.get().await()
            val categorias = querySnapshot.documents.mapNotNull { document ->
                document.toObject(Categoria::class.java)
            }
            emit(categorias)
        } catch (e: Exception) {
            emit(emptyList())
        }
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
        categoria.id?.let { id ->
            categoriaCollection.document(id.toString()).delete().await()
        } ?: run {
            throw IllegalArgumentException("A categoria precisa de um ID válido para ser deletada")
        }
    }
}
