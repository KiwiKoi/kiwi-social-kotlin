package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.entity.ConversationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConversationRepository : JpaRepository<ConversationEntity, String> {
}