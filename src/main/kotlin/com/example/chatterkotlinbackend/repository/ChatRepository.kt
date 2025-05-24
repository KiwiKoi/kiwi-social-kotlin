package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.entity.ChatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChatRepository : JpaRepository<ChatEntity, String> {
    @Query("SELECT c FROM ChatEntity c JOIN c.participants u WHERE c.id = :chatId AND u.id = :userId")
    fun findByIdAndParticipantId(
        @Param("chatId") chatId: String,
        @Param("userId") userId: String
    ): Optional<ChatEntity>


    @Query("SELECT c FROM ChatEntity c JOIN c.participants u WHERE u.id = :participantId")
    fun findAllByUserId(@Param("participantId") participantId: String): List<ChatEntity>
}