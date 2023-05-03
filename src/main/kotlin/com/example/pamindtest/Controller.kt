package com.example.pamindtest

import com.example.pamindtest.logins.LoginRepository
import com.example.pamindtest.model.*
import com.example.pamindtest.user.UserService
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class Controller(private val userService: UserService, private val loginRepository: LoginRepository) {

    private val logger = LoggerFactory.getLogger(Controller::class.java)


    @PostMapping("users/register")
    fun registerUser(@RequestBody registerUser: RegisterUser): User {
        logger.info("Registering user {}", registerUser)

        return userService.registerUser(registerUser)
    }

    @PostMapping("login")
    fun login(@RequestBody login: Login, request: HttpServletRequest): User {

        request.login(login.userName, login.password)


        val loggedInUserName = request.username()
        logger.info("User {} logged in", loggedInUserName)
        return User(loggedInUserName)
    }

    @PostMapping("logout")
    fun logout(request: HttpServletRequest) {
        logger.info("User {} logged out", request.username())
        request.logout()
    }

    // Probably take a user as an input to fetch logins for a specific user
    // Use the authenticated user for simplicity
    @GetMapping("logins")
    fun listMyLogins(request: HttpServletRequest): Logins {
        val userName = request.username()
        return Logins(loginRepository.retrieveLatestLogins(userName).take(5).map { UserLogin(User(userName), it) })
    }

    private fun HttpServletRequest.username(): String {
        val authentication = this.userPrincipal as Authentication
        val user = authentication.principal as UserDetails
        return user.username
    }
}