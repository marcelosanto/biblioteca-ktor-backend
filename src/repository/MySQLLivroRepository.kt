package com.xyz.marcelosantos.repository

import com.xyz.marcelosantos.database.DatabaseMananger
import com.xyz.marcelosantos.entities.Livro
import com.xyz.marcelosantos.entities.LivroDraft

class MySQLLivroRepository : LivroRepository {

    private val database = DatabaseMananger()

    override fun getAllLivros(): List<Livro> {
        return database.getAllLivros().map { Livro(it.id, it.titulo, it.editora, it.foto, listOf(it.autores)) }
    }

    override fun getLivro(id: Int): Livro? {
        TODO("Not yet implemented")
    }

    override fun addLivro(draft: LivroDraft): Livro {
        TODO("Not yet implemented")
    }

    override fun updateLivro(id: Int, draft: LivroDraft): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeLivro(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}
