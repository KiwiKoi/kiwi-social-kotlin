package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<PostEntity, Long?> {
    fun findById(id: String?): Optional<PostEntity>
    fun findByAuthorId(id: String?): List<PostEntity>
}
