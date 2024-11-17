package br.edu.up.appdereceitas.dados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receitas")
data class Receita(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descricao: String,
    val ingredientes: List<String>,
    val preparo: String
)
