package com.example.pamindtest.logins

import com.example.pamindtest.model.User

interface LoginRepository {

    fun storeLogin(userName : String, timestamp: Long)

    fun retrieveLatestLogins(userName: String) : List<Long>
}