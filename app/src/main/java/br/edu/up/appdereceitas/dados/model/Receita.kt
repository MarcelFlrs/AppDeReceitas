package br.edu.up.appdereceitas.dados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receitas")
data class Receita(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val titulo: String,
    val descricao: String,
    val ingredientes: List<String>,
    val preparo: String,
    val favoritado: Boolean = false,
    val categoriaId: Int? = null
){
    constructor() : this(null, "", "", listOf(), "", false, null)
}
