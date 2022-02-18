package com.xyz.marcelosantos

import com.xyz.marcelosantos.entities.Livro
import com.xyz.marcelosantos.repository.InMemoryLivroRepository
import com.xyz.marcelosantos.repository.LivroRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {

        install(CallLogging)
        install(ContentNegotiation){
            gson{
                setPrettyPrinting()
            }
        }

        val repository: LivroRepository = InMemoryLivroRepository()

        get("/obras") {
            call.respond(repository.getAllLivros())
        }

        get("/obras/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if(id==null) {
                call.respond(HttpStatusCode.BadRequest, "id informado é invalido")
                return@get
            }

            val livro = repository.getLivro(id)

            if (livro == null){
                call.respond(HttpStatusCode.NotFound, "Livro não encontrado")
                return@get
            }

            call.respond(livro)
        }

        post("/obras") {}

        put("/obras/{id}") {  }

        delete("/obras/{id}") {}
    }
}
