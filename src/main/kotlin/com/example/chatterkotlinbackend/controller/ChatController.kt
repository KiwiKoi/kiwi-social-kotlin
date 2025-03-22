package com.example.chatterkotlinbackend.controller

import com.example.chatterkotlinbackend.dto.MessageDTO
import com.example.chatterkotlinbackend.service.ChatService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatController(private val chatService: ChatService, private val objectMapper: ObjectMapper) {
    private val logger = LoggerFactory.getLogger(ChatController::class.java)

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    fun sendMessage(@RequestBody messageDTO: MessageDTO): MessageDTO {
        val savedMessage = chatService.saveMessage(messageDTO)

        val jsonMessage = objectMapper.writeValueAsString(savedMessage)
        logger.info("Sending JSON message: $jsonMessage")

        return savedMessage
    }

    @GetMapping("/messages")
    fun getAllMessages(): ResponseEntity<List<MessageDTO>> {
        val messages = chatService.getAllMessages()
        return ResponseEntity.ok(messages)
    }
}