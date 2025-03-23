package com.example.chatterkotlinbackend.dto

class ConversationDTO(
    var id: String,
    val messages: List<MessageDTO>,
    var contactId: String
)