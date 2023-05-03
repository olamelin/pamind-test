package com.example.pamindtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationEventPublisher
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@SpringBootApplication
class PamindTestApplication {

    @Bean
    fun httpSecurity(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

        http.authorizeHttpRequests {
            it.requestMatchers("/api/users/register", "/api/login").permitAll()
                .requestMatchers("**").authenticated()
                .anyRequest().denyAll()
        }

        return http.build()
    }

    @Bean
    fun userDetailService(): UserDetailsManager {
        return InMemoryUserDetailsManager()
    }

    @Bean
    fun authenticationEventPublisher(applicationEventPublisher: ApplicationEventPublisher?): AuthenticationEventPublisher {
        return DefaultAuthenticationEventPublisher(applicationEventPublisher)
    }

}

fun main(args: Array<String>) {
    runApplication<PamindTestApplication>(*args)
}
