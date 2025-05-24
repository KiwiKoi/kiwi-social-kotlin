package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.entity.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<MessageEntity, String> {
    @Query("SELECT m FROM MessageEntity m LEFT JOIN FETCH m.sender WHERE m.chatId = :chatId")
    fun findByChatIdWithSender(@Param("chatId") chatId: String): List<MessageEntity>

}