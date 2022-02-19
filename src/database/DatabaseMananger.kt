package com.xyz.marcelosantos.database

import io.github.cdimascio.dotenv.dotenv
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseMananger {

    private val dotenv = dotenv()


    //config
    private val hostname = dotenv["HOSTNAME"]
    private val databaseName = dotenv["DATA_BASE"]
    private val username = dotenv["USERNAME"]
    private val password = dotenv["PASSWORD"]

    //database
    private val ktormDatabase: Database


    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllLivros(): List<DBLivroEntity> {
        return ktormDatabase.sequenceOf(DBLivroTable).toList()
    }
}
