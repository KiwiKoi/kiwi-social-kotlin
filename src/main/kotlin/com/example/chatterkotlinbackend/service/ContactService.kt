package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.dto.ContactDTO
import com.example.chatterkotlinbackend.entity.ContactEntity
import com.example.chatterkotlinbackend.entity.ConversationEntity
import com.example.chatterkotlinbackend.mapper.ContactMapper
import com.example.chatterkotlinbackend.repository.ContactRepository
import com.example.chatterkotlinbackend.repository.ConversationRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class ContactService(
    private val contactRepository: ContactRepository,
    private val mapper: ContactMapper,
    private val userRepository: UserRepository,
    private val conversationRepository: ConversationRepository
) {

    @Transactional
    fun getContactsForUser(userId: String): List<ContactDTO> {
        val contacts = contactRepository.findByUser1IdOrUser2Id(userId, userId)
        return mapper.toDTO(contacts)
    }

    @Transactional
    fun sendContactRequest(user1Id: String, user2Id: String) {
        val user1 = userRepository.findById(user1Id).orElseThrow { RuntimeException("User not found") }
        val user2 = userRepository.findById(user2Id).orElseThrow { RuntimeException("User not found") }

        val existingContact =
            contactRepository.findByUser1IdAndUser2Id(user1.id, user2.id) ?: contactRepository.findByUser1IdAndUser2Id(
                user2.id,
                user1.id
            )

        if (existingContact != null) {
            throw IllegalArgumentException("Contact already exists")
        }

        val contact = ContactEntity(user1 = user1, user2 = user2, status = ContactEntity.ContactStatus.PENDING)
        val savedContact = contactRepository.save(contact)

        val conversation = ConversationEntity(contactId = contact.id)

        val savedConversation = conversationRepository.save(conversation)

        savedContact.conversation = savedConversation
        contactRepository.save(savedContact)
    }

    // Accept a contact request
    fun acceptContactRequest(userId: String, contactId: String) {
        val contactRequest = contactRepository.findByUser1IdAndUser2Id(userId, contactId)
            ?: throw RuntimeException("Contact request not found")

        contactRequest.status = ContactEntity.ContactStatus.ACCEPTED
        contactRepository.save(contactRequest)
    }

    // Block a contact
    fun blockContact(userId: String, contactId: String) {
        val contactRequest = contactRepository.findByUser1IdAndUser2Id(userId, contactId)
            ?: throw RuntimeException("Contact not found")

        contactRequest.status = ContactEntity.ContactStatus.BLOCKED
        contactRepository.save(contactRequest)
    }
}