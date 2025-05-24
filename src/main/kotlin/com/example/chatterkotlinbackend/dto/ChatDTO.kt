package com.example.chatterkotlinbackend.dto

class ChatDTO(
    var id: String,
    val messages: List<MessageDTO>,
    var participants: List<UserDTO>
)