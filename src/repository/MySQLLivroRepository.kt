package com.xyz.marcelosantos.repository

import com.xyz.marcelosantos.database.DatabaseMananger
import com.xyz.marcelosantos.entities.Livro
import com.xyz.marcelosantos.entities.LivroDraft

class MySQLLivroRepository : LivroRepository {

    private val database = DatabaseMananger()

    override fun getAllLivros(): List<Livro> {
        return database.getAllLivros().map { Livro(it.id, it.titulo, it.editora, it.foto, it.autores) }
    }

    override fun getLivro(id: Int): Livro? {
        return database.getLivro(id)?.let { Livro(it.id, it.titulo, it.editora, it.foto, it.autores) }
    }

    override fun addLivro(draft: LivroDraft): Livro {
        return database.addLivro(draft)
    }

    override fun updateLivro(id: Int, draft: LivroDraft): Boolean {
        return database.updateLivro(id, draft)
    }

    override fun removeLivro(id: Int): Boolean {
        return database.removeLivro(id)
    }
}
