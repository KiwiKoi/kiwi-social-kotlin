package com.example.chatterkotlinbackend.controller

import com.example.chatterkotlinbackend.dto.CommentCreationDTO
import com.example.chatterkotlinbackend.dto.CommentDTO
import com.example.chatterkotlinbackend.entity.CommentEntity
import com.example.chatterkotlinbackend.repository.CommentRepository
import com.example.chatterkotlinbackend.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments/")
class CommentController {
    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    lateinit var commentRepository: CommentRepository


    @PostMapping
    fun createComment(
        @RequestBody comment: CommentCreationDTO,
        @RequestParam userId: String,
        @RequestParam postId: String,
    ): CommentDTO {
        return commentService.createComment(comment, userId, postId)
    }

    @GetMapping("/{postId}")
    fun getCommentsByPost(
        @PathVariable("postId") postId: String
    ): ResponseEntity<List<CommentEntity>> {
        return try {
            val comments: List<CommentEntity> = commentRepository.findByPostId(postId)

            if (comments.isEmpty()) {
                ResponseEntity(HttpStatus.NO_CONTENT)
            } else {
                ResponseEntity(comments, HttpStatus.OK)
            }
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}