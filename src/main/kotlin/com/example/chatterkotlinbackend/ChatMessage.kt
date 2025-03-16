package com.example.chatterkotlinbackend

import java.time.LocalDateTime

data class ChatMessage(
    val sender: String,
    val content: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)