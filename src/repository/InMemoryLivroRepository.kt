package com.xyz.marcelosantos.repository

/*class InMemoryLivroRepository : LivroRepository {

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

}*/
