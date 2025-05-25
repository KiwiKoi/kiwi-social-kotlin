package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.dto.CommentCreationDTO
import com.example.chatterkotlinbackend.dto.CommentDTO
import com.example.chatterkotlinbackend.entity.CommentEntity
import com.example.chatterkotlinbackend.mapper.CommentMapper
import com.example.chatterkotlinbackend.mapper.UserMapper
import com.example.chatterkotlinbackend.repository.CommentRepository
import com.example.chatterkotlinbackend.repository.PostRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val mapper: CommentMapper,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) {

    @Transactional
    fun createComment(dto: CommentCreationDTO, userId: String, postId: String): CommentDTO {
        val author = userRepository.findById(userId).orElseThrow {
            RuntimeException("User with ID $userId not found.")
        }

        val post = postRepository.findById(postId).orElseThrow {
            RuntimeException("Post with ID $postId not found.")
        }

        val commentEntity = CommentEntity(body = dto.body, author = author, post = post)

        val savedComment = commentRepository.save(commentEntity)

        return mapper.toDto(savedComment)
    }
}