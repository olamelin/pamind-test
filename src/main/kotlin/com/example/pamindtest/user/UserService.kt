package com.example.pamindtest.user

import com.example.pamindtest.model.RegisterUser
import com.example.pamindtest.model.User

interface UserService {

    fun registerUser(registerUser: RegisterUser) : User
}