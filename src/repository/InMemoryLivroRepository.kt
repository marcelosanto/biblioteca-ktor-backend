package com.xyz.marcelosantos.repository

import com.xyz.marcelosantos.entities.Livro

class InMemoryLivroRepository: LivroRepository {
    private  val livros = listOf<Livro>(
        Livro(1, "Harry Potter",  "Rocco", "https://i.imgur.com/UH3IPXw.jpg",  listOf("JK Rowling", "...")),
        Livro(2, "Harry Potter e a Pedra filosofal",  "Rocco", "https://i.imgur.com/UH3IPXw.jpg",  listOf("JK Rowling", "...")),
        Livro(3, "Harry Potter e o prisioneiro de askaban",  "Rocco", "https://i.imgur.com/UH3IPXw.jpg",  listOf("JK Rowling", "...")),
        Livro(4, "Harry Potter calice de fogo",  "Rocco", "https://i.imgur.com/UH3IPXw.jpg",  listOf("JK Rowling", "...")),
        Livro(5, "Harry Potter e a ordem da fenix",  "Rocco", "https://i.imgur.com/UH3IPXw.jpg",  listOf("JK Rowling", "...")),
    )

    override fun getAllLivros(): List<Livro> {
       return livros
    }

    override fun getLivro(id: Int): Livro? {
        return livros.firstOrNull { it.id == id }
    }
}
