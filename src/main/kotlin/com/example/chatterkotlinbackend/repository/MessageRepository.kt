package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.entity.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<MessageEntity, String> {
    fun findByConversationId(conversationId: String): List<MessageEntity>
}