package com.xyz.marcelosantos.repository

import com.xyz.marcelosantos.entities.Livro
import com.xyz.marcelosantos.entities.LivroDraft

class InMemoryLivroRepository : LivroRepository {
    private val livros = mutableListOf<Livro>(
        Livro(1, "Harry Potter", "Rocco", "https://i.imgur.com/UH3IPXw.jpg", listOf("JK Rowling", "...")),
        Livro(
            2,
            "Harry Potter e a Pedra filosofal",
            "Rocco",
            "https://i.imgur.com/UH3IPXw.jpg",
            listOf("JK Rowling", "...")
        ),
        Livro(
            3,
            "Harry Potter e o prisioneiro de askaban",
            "Rocco",
            "https://i.imgur.com/UH3IPXw.jpg",
            listOf("JK Rowling", "...")
        ),
        Livro(
            4, "Harry Potter calice de fogo", "Rocco", "https://i.imgur.com/UH3IPXw.jpg", listOf("JK Rowling", "...")
        ),
        Livro(
            5,
            "Harry Potter e a ordem da fenix",
            "Rocco",
            "https://i.imgur.com/UH3IPXw.jpg",
            listOf("JK Rowling", "...")
        ),
    )

    override fun getAllLivros(): List<Livro> {
        return livros
    }

    override fun getLivro(id: Int): Livro? {
        return livros.firstOrNull { it.id == id }
    }

    override fun addLivro(draft: LivroDraft): Livro {
        val livro = Livro(
            id = livros.size + 1,
            titulo = draft.titulo,
            editora = draft.editora,
            foto = draft.foto,
            autores = draft.autores
        )

        livros.add(livro)

        return livro
    }

    override fun updateLivro(id: Int, draft: LivroDraft): Boolean {
        val livro = livros.firstOrNull { it.id == id } ?: return false

        livro.titulo = draft.titulo
        livro.editora = draft.editora
        livro.foto = draft.foto
        livro.autores = draft.autores

        return true

    }

    override fun removeLivro(id: Int) = livros.removeIf { it.id == id }

}
