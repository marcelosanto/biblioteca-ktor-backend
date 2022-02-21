package com.xyz.marcelosantos

import com.xyz.marcelosantos.authentication.JwtConfig
import com.xyz.marcelosantos.entities.LivroDraft
import com.xyz.marcelosantos.entities.LoginBody
import com.xyz.marcelosantos.repository.InMemoryUserRepository
import com.xyz.marcelosantos.repository.LivroRepository
import com.xyz.marcelosantos.repository.MySQLLivroRepository
import com.xyz.marcelosantos.repository.UserRepository
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val jwtConfig = JwtConfig(System.getenv("KTOR_BIBLIOTECA_JWT_SECRET"))

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    routing {

        install(CallLogging)
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        install(Authentication) {
            jwt {
                jwtConfig.configureKtorFeature(this)
            }
        }

        val repository: LivroRepository = MySQLLivroRepository()
        val userRepository: UserRepository = InMemoryUserRepository()

        get("/") {
            call.respondText("Bem vindo ao seu Sistema de gerenciamento de biblioteca")
        }

        post("/login") {
            val loginBody = call.receive<LoginBody>()

            val user = userRepository.getUser(loginBody.username, loginBody.password)

            if (user == null) {
                call.respond(HttpStatusCode.Unauthorized, "Credenciais inválidas")
                return@post
            }

            val token = jwtConfig.generateToken(JwtConfig.JwtUser(user.userId, user.username))
            call.respond(token)
        }

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

        authenticate {
            get("/me") {
                val user = call.authentication.principal as JwtConfig.JwtUser
                call.respond(user)
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
}
