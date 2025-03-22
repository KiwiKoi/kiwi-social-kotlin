package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.PostDTO
import com.example.chatterkotlinbackend.entity.PostEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface PostMapper {


    @Mapping(source = "author.id", target = "authorId")
    fun toDto(entity: PostEntity): PostDTO;
}