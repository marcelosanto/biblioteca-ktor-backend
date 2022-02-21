package com.xyz.marcelosantos.database

import com.xyz.marcelosantos.entities.Livro
import com.xyz.marcelosantos.entities.LivroDraft
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseMananger {

    //config
    private val hostname = System.getenv("HOSTNAME")
    private val databaseName = System.getenv("DATA_BASE")
    private val username = System.getenv("USER_NAME")
    private val password = System.getenv("PASSWORD")

    //database
    private val ktormDatabase: Database


    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllLivros(): List<DBLivroEntity> {
        return ktormDatabase.sequenceOf(DBLivroTable).toList()
    }

    fun getLivro(id: Int): DBLivroEntity? {
        return ktormDatabase.sequenceOf(DBLivroTable)
            .firstOrNull { it.id eq id }
    }

    fun addLivro(draft: LivroDraft): Livro {
        val insertId = ktormDatabase.insertAndGenerateKey(DBLivroTable) {
            set(DBLivroTable.titulo, draft.titulo)
            set(DBLivroTable.editora, draft.editora)
            set(DBLivroTable.foto, draft.foto)
            set(DBLivroTable.autores, draft.autores)
        } as Int

        return Livro(insertId, draft.titulo, draft.editora, draft.foto, draft.autores)
    }

    fun updateLivro(id: Int, draft: LivroDraft): Boolean {
        val updateLivro = ktormDatabase.update(DBLivroTable) {
            set(DBLivroTable.titulo, draft.titulo)
            set(DBLivroTable.editora, draft.editora)
            set(DBLivroTable.foto, draft.foto)
            set(DBLivroTable.autores, draft.autores)
            where { it.id eq id }
        }

        return updateLivro > 0
    }

    fun removeLivro(id: Int): Boolean {
        val deleteLivro = ktormDatabase.delete(DBLivroTable) {
            it.id eq id
        }

        return deleteLivro > 0
    }
}
