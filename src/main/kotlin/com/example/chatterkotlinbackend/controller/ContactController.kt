package com.example.chatterkotlinbackend.controller

import com.example.chatterkotlinbackend.dto.ContactDTO
import com.example.chatterkotlinbackend.service.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactController {
//    @Autowired
//    private lateinit var contactService: ContactService

//    @GetMapping("/{userId}")
//    fun getContacts(@PathVariable userId: String): List<ContactDTO> {
//        return contactService.getContactsForUser(userId)
//    }
//
//    @PostMapping("/{userId}/add/{contactId}")
//    fun sendContactRequest(@PathVariable userId: String, @PathVariable contactId: String) {
//        contactService.sendContactRequest(userId, contactId)
//    }
//
//    @PostMapping("/{userId}/accept/{contactId}")
//    fun acceptContact(@PathVariable userId: String, @PathVariable contactId: String) {
//        contactService.acceptContactRequest(userId, contactId)
//    }
//
//    @PostMapping("/{userId}/block/{contactId}")
//    fun blockContact(@PathVariable userId: String, @PathVariable contactId: String) {
//        contactService.blockContact(userId, contactId)
//    }
}