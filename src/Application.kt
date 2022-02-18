package com.xyz.marcelosantos

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {
        get("/obras") {
            call.respondText("Oiee Man" )
        }

        get("/obras/{id}") {
            call.respondText("Oiee Man" )
        }

        post("/obras") {}

        put("/obras/{id}") {  }

        delete("/obras/{id}") {}
    }
}
