package com.example.chatterkotlinbackend

import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader
import java.time.LocalDateTime

@Controller
class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
   fun sendMessage(message: ChatMessage, @AuthenticationPrincipal principal: Any?): ChatMessage {
        println("Authenticated User: $principal")

        return message
    }
}