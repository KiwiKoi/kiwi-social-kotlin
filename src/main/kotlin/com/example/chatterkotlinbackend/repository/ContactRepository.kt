package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.entity.ContactEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<ContactEntity, String> {
    @EntityGraph(attributePaths = ["user1", "user2", "conversation"])
    fun findByUser1IdOrUser2Id(user1Id: String, user2Id: String): List<ContactEntity>
    fun findByUser1IdAndUser2Id(user1Id: String, user2Id: String): ContactEntity?
}