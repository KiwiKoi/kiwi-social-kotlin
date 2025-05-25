package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.CommentDTO
import com.example.chatterkotlinbackend.entity.CommentEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [UserMapper::class])
interface CommentMapper {

    @Mapping(source = "post.id", target = "postId")
    fun toDto(entity: CommentEntity): CommentDTO
    fun toDto(entities: List<CommentEntity>): List<CommentDTO>
}