package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.entity.UserEntity
import com.example.chatterkotlinbackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserRepositoryService(private val userRepository: UserRepository) {

    @Transactional
    fun getUserById(userId: String): UserEntity {
        val user = userRepository.findById(userId);
        if (user.isPresent) {
            return user.get();
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }

    }
}