package com.example.pamindtest.model


data class RegisterUser(
    val userName: String,
    val password: String
) {

    // Make sure the password is not accidentally logged
    override fun toString(): String {
        return "RegisterUser(userName='$userName', password='*****')"
    }
}

data class Login(
    val userName : String,
    val password: String
) {
    // Make sure the password is not accidentally logged
    override fun toString(): String {
        return "Login(userName='$userName', password='*****')"
    }
}

data class User(
    val userName: String
)


data class Logins(val userLogins: List<UserLogin>)
data class UserLogin(val user: User, val timestamp : Long)