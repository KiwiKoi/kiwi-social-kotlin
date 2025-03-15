package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long?> {
    fun findByPostId(id:String): List<CommentEntity>
}