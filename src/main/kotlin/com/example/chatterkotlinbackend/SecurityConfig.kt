package com.example.chatterkotlinbackend

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Value("\${firebase.project.id}")
    lateinit var firebaseProjectId: String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                // Allow the WebSocket connection to be accessed by authenticated users only
//                auth.requestMatchers(AntPathRequestMatcher("/ws/**")).authenticated() // WebSocket endpoints
                auth.anyRequest().permitAll()  // Other requests can be public
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                }
            }
        return http.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return JwtDecoders.fromIssuerLocation("https://securetoken.google.com/$firebaseProjectId")
    }

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        // You can configure custom authorities here if needed
        return jwtAuthenticationConverter
    }
}