package com.example.chatterkotlinbackend.dto

import com.example.chatterkotlinbackend.entity.ContactEntity
import java.time.OffsetDateTime

data class ContactDTO(
    val id: String,
    val requesterId: String,
    val recipientId: String,
    val requesterUsername: String?,
    val recipientUsername: String?,
    val status: ContactEntity.ContactStatus,
    val createdAt: OffsetDateTime,
)