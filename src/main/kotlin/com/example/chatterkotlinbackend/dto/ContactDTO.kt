package com.example.chatterkotlinbackend.dto

import com.example.chatterkotlinbackend.entity.ContactEntity

data class ContactDTO(
    var id: String,
    val user1Id: String,
    val user2Id: String,
    val user1Username: String,
    val user2Username: String,
    val status: ContactEntity.ContactStatus,
    val conversationId: String,
)