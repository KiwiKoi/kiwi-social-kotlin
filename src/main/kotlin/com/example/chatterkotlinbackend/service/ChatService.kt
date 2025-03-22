package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.dto.MessageDTO
import com.example.chatterkotlinbackend.mapper.MessageMapper
import com.example.chatterkotlinbackend.repository.MessageRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ChatService(private val messageRepository: MessageRepository, private val userRepository: UserRepository, private val mapper: MessageMapper) {

    fun saveMessage(dto: MessageDTO): MessageDTO {
        if (dto.senderId.isBlank()) {
            throw IllegalArgumentException("Sender ID cannot be null or empty")
        }

        val user = userRepository.findById(dto.senderId)
            .orElseThrow { IllegalArgumentException("User not found with ID: ${dto.senderId}") }

        val messageEntity =  mapper.toEntity(dto, user)
        val savedMessage = messageRepository.save(messageEntity)
        return mapper.toDto(savedMessage)
    }

    fun getAllMessages(): List<MessageDTO> {
        return messageRepository.findAll().map { mapper.toDto(it) }
    }
}