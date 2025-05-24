package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.GoogleAuthUser
import com.example.chatterkotlinbackend.dto.UserDTO
import com.example.chatterkotlinbackend.entity.UserEntity
import com.example.chatterkotlinbackend.mapper.UserMapper
import com.example.chatterkotlinbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val mapper: UserMapper) {

    fun createUser(googleAuthUser: GoogleAuthUser): UserDTO {
        val newUser = UserEntity(id = googleAuthUser.uid, email = googleAuthUser.email)
        val createdUser = userRepository.save(newUser)
        return mapper.toDto(createdUser);
    }

}