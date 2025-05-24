package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.mapper.ContactMapper
import com.example.chatterkotlinbackend.repository.ChatRepository
import com.example.chatterkotlinbackend.repository.ContactRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class ContactService(
    private val contactRepository: ContactRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository,
    private val mapper: ContactMapper,
) {

//    @Transactional
//    fun getContactsForUser(requesterId: String): List<ContactDTO> {
//        val contacts = contactRepository.findByRequesterIdOrRecipientId(requesterId, requesterId)
//        return mapper.toDTO(contacts)
//    }
//
//    @Transactional
//    fun sendContactRequest(requesterId: String, recipientId: String) {
//        val requester = userRepository.findById(requesterId).orElseThrow { RuntimeException("User not found") }
//        val recipient = userRepository.findById(recipientId).orElseThrow { RuntimeException("User not found") }
//
//        val existingContact =
//            contactRepository.findByRequesterIdAndRecipientId(requester.id, recipient.id) ?: contactRepository.findByRequesterIdAndRecipientId(
//                recipient.id,
//                requester.id
//            )
//
//        if (existingContact != null) {
//            throw IllegalArgumentException("Contact already exists")
//        }
//
//        val contact = ContactEntity(requester = requester, recipient = recipient, status = ContactEntity.ContactStatus.PENDING)
//        val savedContact = contactRepository.save(contact)
//
//        val chat = ChatEntity(contactId = contact.id)
//
//        val savedChat = chatRepository.save(chat)
//
//        savedContact.chat = savedChat
//        contactRepository.save(savedContact)
//    }
//
//    fun acceptContactRequest(requesterId: String, recipientId: String) {
//        val contactRequest = contactRepository.findByRequesterIdAndRecipientId(requesterId, recipientId)
//            ?: throw RuntimeException("Contact request not found")
//
//        contactRequest.status = ContactEntity.ContactStatus.ACCEPTED
//        contactRepository.save(contactRequest)
//    }
//
//    fun blockContact(requesterId: String, recipientId: String) {
//        val contactRequest = contactRepository.findByRequesterIdAndRecipientId(requesterId, recipientId)
//            ?: throw RuntimeException("Contact not found")
//
//        contactRequest.status = ContactEntity.ContactStatus.BLOCKED
//        contactRepository.save(contactRequest)
//    }
}