package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.ChatDTO
import com.example.chatterkotlinbackend.entity.ChatEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ChatMapper {

    fun toEntity(dto: ChatDTO): ChatEntity

    fun toDto(entity: ChatEntity): ChatDTO

    fun toDto(entities: List<ChatEntity>): List<ChatDTO>
}