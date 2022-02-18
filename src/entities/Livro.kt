package com.xyz.marcelosantos.entities

data class Livro(
    val id: Int,
    val titulo: String,
    val editora: String,
    val foto: String,
    val autores: List<String>
)
