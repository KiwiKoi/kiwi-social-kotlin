package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.dto.MessageDTO
import com.example.chatterkotlinbackend.mapper.MessageMapper
import com.example.chatterkotlinbackend.repository.MessageRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatService(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
    private val mapper: MessageMapper,
) {

    @Transactional
    fun saveMessage(dto: MessageDTO): MessageDTO {
        if (dto.senderId.isBlank()) {
            throw IllegalArgumentException("Sender ID cannot be null or empty")
        }

        val user = userRepository.findById(dto.senderId)
            .orElseThrow { IllegalArgumentException("User not found with ID: ${dto.senderId}") }

        dto.id = UUID.randomUUID().toString();
        val messageEntity = mapper.toEntity(dto, user)

        val savedMessage = messageRepository.save(messageEntity)
        return mapper.toDto(savedMessage)
    }

    fun getAllMessages(): List<MessageDTO> {
        return messageRepository.findAll().map { mapper.toDto(it) }
    }

    @Transactional
    fun getMessagesByConversationId(conversationId: String): List<MessageDTO> {
        val messages = messageRepository.findByConversationId(conversationId) ?: emptyList()
        return mapper.toDto(messages)
    }
}