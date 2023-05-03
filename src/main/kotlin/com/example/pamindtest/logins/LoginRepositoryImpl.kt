package com.example.pamindtest.logins

import org.springframework.stereotype.Repository

@Repository
class LoginRepositoryImpl : LoginRepository {

    // Simple and not particularly safe implementation works for this example.
    private val loginsByUserName = mutableMapOf<String, MutableList<Long>>()

    override fun storeLogin(userName: String, timestamp: Long) {
        val logins = loginsByUserName.getOrDefault(userName, mutableListOf())
        logins.add(timestamp)
        logins.sortDescending()
        loginsByUserName[userName] = logins
    }

    override fun retrieveLatestLogins(userName: String): List<Long> {
        return loginsByUserName[userName] ?: emptyList()
    }


}

