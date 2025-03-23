package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.entity.ConversationEntity
import com.example.chatterkotlinbackend.repository.ContactRepository
import com.example.chatterkotlinbackend.repository.ConversationRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class ConversationService(
    private val contactRepository: ContactRepository,
    private val conversationRepository: ConversationRepository
) {


    @Transactional
    fun startConversation(userId1: String, userId2: String): ConversationEntity {
        val contact = contactRepository.findByUser1IdAndUser2Id(userId1, userId2)
            ?: throw IllegalArgumentException("Both users must be contacts to start a conversation")

        val conversation = ConversationEntity(contactId = contact.id)

        return conversationRepository.save(conversation)

    }

    @Transactional
    fun getConversation(userId1: String, userId2: String): ConversationEntity {
        val contact = contactRepository.findByUser1IdAndUser2Id(userId1, userId2)
            ?: throw RuntimeException("Contact for users $userId1 and $userId2 not found")


        val conversation = contact.conversation
            ?: throw RuntimeException("Conversation not found for contact ${contact.id}")

        return conversation
    }

}