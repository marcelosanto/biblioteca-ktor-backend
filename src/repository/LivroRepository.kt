package com.xyz.marcelosantos.repository

import com.xyz.marcelosantos.entities.Livro
import com.xyz.marcelosantos.entities.LivroDraft

interface LivroRepository {

    fun getAllLivros(): List<Livro>

    fun getLivro(id: Int): Livro?

    fun addLivro(draft: LivroDraft): Livro

    fun updateLivro(id: Int, draft: LivroDraft): Boolean

    fun removeLivro(id: Int): Boolean
}
