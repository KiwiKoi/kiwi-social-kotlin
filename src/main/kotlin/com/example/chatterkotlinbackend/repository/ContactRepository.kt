package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.entity.ContactEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<ContactEntity, String> {
    @EntityGraph(attributePaths = ["requester", "recipient"])
    fun findByRequesterIdOrRecipientId(requesterId: String, recipientId: String): List<ContactEntity>
    fun findByRequesterIdAndRecipientId(requesterId: String, recipientId: String): ContactEntity?
}