package com.example.chatterkotlinbackend.service

import com.example.chatterkotlinbackend.dto.PostCreationDTO
import com.example.chatterkotlinbackend.dto.PostDTO
import com.example.chatterkotlinbackend.entity.PostEntity
import com.example.chatterkotlinbackend.mapper.PostMapper
import com.example.chatterkotlinbackend.repository.PostRepository
import com.example.chatterkotlinbackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
    private val mapper: PostMapper,
    private val userRepository: UserRepository
) {

    @Transactional
    fun createPost(dto: PostCreationDTO, userId: String): PostDTO {
        val author = userRepository.findById(userId).orElseThrow {
            RuntimeException("User with ID $userId not found.")
        }

        val postEntity = PostEntity(body = dto.body, author = author)

        val savedPost = postRepository.save(postEntity)

        return mapper.toDto(savedPost)
    }

    fun favoritePost(postId: String, userId: String) {
        val post = postRepository.findById(postId).orElseThrow { RuntimeException("Post not found") }
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }

        if (post.favoritedBy.add(user)) {
            user.favorites.add(post)
            userRepository.save(user)
        }
    }

    fun unfavoritePost(postId: String, userId: String) {
        val post = postRepository.findById(postId).orElseThrow { RuntimeException("Post not found") }
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }

        if (post.favoritedBy.remove(user)) {
            user.favorites.remove(post)
            postRepository.save(post)
        }
    }

    fun getFavoritesByUser(userId: String): List<PostEntity> {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        return user.favorites.toList()
    }

    fun isPostFavoritedByUser(postId: String, userId: String): Boolean {
        val post = postRepository.findById(postId).orElseThrow { RuntimeException("Post not found") }
        return post.favoritedBy.any { it.id == userId }
    }
}