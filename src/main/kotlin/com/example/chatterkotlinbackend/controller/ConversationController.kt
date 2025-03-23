package com.example.chatterkotlinbackend.controller

import com.example.chatterkotlinbackend.dto.ConversationDTO
import com.example.chatterkotlinbackend.mapper.ConversationMapper
import com.example.chatterkotlinbackend.service.ConversationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/conversations")
class ConversationController(
    private val mapper: ConversationMapper,
    private val service: ConversationService
) {

    @GetMapping("/{userId1}/{userId2}")
    fun getConversation(@PathVariable userId1: String, @PathVariable userId2: String): ConversationDTO {
        return mapper.toDto(service.getConversation(userId1, userId2))
    }

    @PostMapping("/start")
    fun startConversation(
        @RequestParam userId1: String,
        @RequestParam userId2: String
    ): ResponseEntity<ConversationDTO> {
        val conversation = service.startConversation(userId1, userId2)

        return ResponseEntity.ok(mapper.toDto(conversation))
    }
}