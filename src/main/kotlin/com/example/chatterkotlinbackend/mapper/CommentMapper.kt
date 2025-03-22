package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.CommentDTO
import com.example.chatterkotlinbackend.entity.CommentEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CommentMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "post.id", target = "postId")
    fun toDto(entity: CommentEntity): CommentDTO
}