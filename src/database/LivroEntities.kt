package com.xyz.marcelosantos.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBLivroTable : Table<DBLivroEntity>("biblioteca") {

    val id = int("id").primaryKey().bindTo { it.id }
    val titulo = varchar("titulo").bindTo { it.titulo }
    val editora = varchar("editora").bindTo { it.editora }
    val foto = varchar("foto").bindTo { it.foto }
    val autores = varchar("autores").bindTo { it.autores }

}

interface DBLivroEntity : Entity<DBLivroEntity> {
    companion object : Entity.Factory<DBLivroEntity>()

    val id: Int
    val titulo: String
    val editora: String
    val foto: String
    val autores: String
}
