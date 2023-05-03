package com.example.pamindtest.user

import com.example.pamindtest.model.RegisterUser
import com.example.pamindtest.model.User
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

@Service
class UserServiceImpl(private val useDetailsManager: UserDetailsManager) : UserService {

    override fun registerUser(registerUser: RegisterUser): User {
        // Use empty list for roles as it is not important in this example
        val springUser = SpringUser.withDefaultPasswordEncoder()
            .username(registerUser.userName)
            .password(registerUser.password)
            .roles("USER")
            .build();
        useDetailsManager.createUser(springUser)
        return User(registerUser.userName)
    }


}