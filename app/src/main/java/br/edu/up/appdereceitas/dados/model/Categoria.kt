package br.edu.up.appdereceitas.dados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nome: String
){
    constructor() : this(null, "")
}
