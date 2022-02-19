package com.xyz.marcelosantos

import com.xyz.marcelosantos.entities.LivroDraft
import com.xyz.marcelosantos.repository.LivroRepository
import com.xyz.marcelosantos.repository.MySQLLivroRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    routing {

        install(CallLogging)
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }

        val repository: LivroRepository = MySQLLivroRepository()

        get("/obras") {
            call.respond(repository.getAllLivros())
        }

        get("/obras/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "id informado é invalido")
                return@get
            }

            val livro = repository.getLivro(id)

            if (livro == null) {
                call.respond(HttpStatusCode.NotFound, "Livro não encontrado")
                return@get
            }

            call.respond(livro)
        }

        post("/obras") {
            val livroDraft = call.receive<LivroDraft>()
            val livro = repository.addLivro(livroDraft)

            call.respond(livro)
        }

        put("/obras/{id}") {
            val livroDraft = call.receive<LivroDraft>()
            val livroId = call.parameters["id"]?.toIntOrNull()

            if (livroId == null) {
                call.respond(HttpStatusCode.BadRequest, "id informado é invalido")
                return@put
            }

            val livroUpdate = repository.updateLivro(livroId, livroDraft)

            if (livroUpdate) {
                call.respond(HttpStatusCode.OK, "As informações do livro foram atualizadas")
            } else {
                call.respond(HttpStatusCode.NotFound, "Livro não encontrado com o id $livroId informado.")
            }
        }

        delete("/obras/{id}") {
            val livroId = call.parameters["id"]?.toIntOrNull()

            if (livroId == null) {
                call.respond(HttpStatusCode.BadRequest, "id informado é invalido")
                return@delete
            }

            val livroRemoved = repository.removeLivro(livroId)
            if (livroRemoved) {
                call.respond(HttpStatusCode.OK, "Livro removido")
            } else {
                call.respond(HttpStatusCode.NotFound, "Livro não encontrado com o id $livroId informado.")
            }

        }
    }
}
