package br.edu.up.appdereceitas.ui.model


data class Receita(
    val nome: String,
    val descricao: String,
    val ingredientes: String,
    val tempoPreparo: String,
    val tempoCozimento: String,
    val tempoDescanso: String,
    val tempoTotal: String,
    val categoria: String
)