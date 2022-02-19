package com.xyz.marcelosantos.repository

class InMemoryUserRepository : UserRepository {

    private val credentialsToUsers = mapOf<String, UserRepository.User>(
        "admin:admin" to UserRepository.User(1, "admin"),
        "marcelo:1234" to UserRepository.User(2, "marcelo")
    )

    override fun getUser(username: String, password: String): UserRepository.User? {
        return credentialsToUsers["$username:$password"]
    }
}
