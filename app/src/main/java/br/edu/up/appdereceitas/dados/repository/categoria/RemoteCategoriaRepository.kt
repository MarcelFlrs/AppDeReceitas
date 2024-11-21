package br.edu.up.appdereceitas.dados.repository.categoria

import br.edu.up.appdereceitas.dados.model.Categoria
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

    override suspend fun buscarCategoriaPorId(idx: Int): Categoria? {
        val documentSnapshot = categoriaCollection.document(idx.toString()).get().await()
        return if (documentSnapshot.exists()) {
            documentSnapshot.toObject(Categoria::class.java)
        } else {
            null
        }
    }

    override suspend fun gravarCategoria(categoria: Categoria) {
        categoriaCollection.document(categoria.id.toString()).set(categoria).await()
    }

    override suspend fun deleteCategoria(categoria: Categoria) {
        categoriaCollection.document(categoria.id.toString()).delete().await()
    }
}
