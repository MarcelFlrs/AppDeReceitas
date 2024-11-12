package br.edu.up.appdereceitas.dados

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val ingredients: String,
    val preparationTime: String,
    val cookingTime: String,
    val totalTime: String
    
)
