package com.example.chatterkotlinbackend.controller

import com.example.chatterkotlinbackend.model.CommentEntity
import com.example.chatterkotlinbackend.model.PostEntity
import com.example.chatterkotlinbackend.model.UserEntity
import com.example.chatterkotlinbackend.repository.CommentRepository
import com.example.chatterkotlinbackend.repository.PostRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/comments/")
class CommentController {
    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var commentRepository: CommentRepository

    @Autowired
    lateinit var userRepository: UserRepository


    @PostMapping
    fun createComment(
        @RequestBody comment: CommentEntity,
        @RequestParam user_id: String,
        @RequestParam postId: String,
    ): ResponseEntity<CommentEntity> {
        return try{
            val author: Optional<UserEntity> = userRepository.findById(user_id)
            val post: Optional<PostEntity> = postRepository.findById(postId)
            if(author.isEmpty){
                return ResponseEntity(null, HttpStatus.BAD_REQUEST)
            }
            comment.author = author.get()
            comment.post = post.get()
            comment.createdAt = LocalDateTime.now()
            val newComment: CommentEntity = commentRepository.save(comment)
            ResponseEntity(newComment, HttpStatus.CREATED)
        } catch (e: RuntimeException) {
            println("Error saving comment: ${e.message}")
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{postId}")
    fun getCommentsByPost(
        @PathVariable("postId") postId: String
    ): ResponseEntity<List<CommentEntity>>{
        return try {
            val comments: List<CommentEntity> = commentRepository.findByPostId(postId)

            if (comments.isEmpty()) {
                ResponseEntity(HttpStatus.NO_CONTENT)
            } else {
                ResponseEntity(comments, HttpStatus.OK)
            }
        } catch(e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}