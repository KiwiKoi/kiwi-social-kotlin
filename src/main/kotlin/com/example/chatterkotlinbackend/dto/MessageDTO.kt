package com.example.chatterkotlinbackend.dto

import java.time.OffsetDateTime


class MessageDTO(
    val senderId: String,
    val content: String,
    val timestamp: OffsetDateTime = OffsetDateTime.now()
)