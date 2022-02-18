package com.xyz.marcelosantos.repository

import com.xyz.marcelosantos.entities.Livro

interface LivroRepository {

    fun getAllLivros(): List<Livro>

    fun getLivro(id: Int): Livro?
}
