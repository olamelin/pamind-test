package com.example.pamindtest.logins

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Instant


@Component
class LoginEventListener(private val loginRepository: LoginRepository) {

    private val logger = LoggerFactory.getLogger(LoginEventListener::class.java)

    @EventListener
    fun onSuccess(success: AuthenticationSuccessEvent) {
        // unsafe cast for this example
        val principal = success.authentication.principal as UserDetails
        val userName = principal.username

        // Capture the time here, not particular accurate but...
        val loginTime = Instant.now()
        logger.info("Successful login event for user {} at {}", userName, loginTime)
        loginRepository.storeLogin(userName, loginTime.toEpochMilli())
    }
}