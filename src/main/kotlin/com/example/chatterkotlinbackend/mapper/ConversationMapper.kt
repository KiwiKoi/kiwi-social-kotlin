package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.ConversationDTO
import com.example.chatterkotlinbackend.entity.ConversationEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ConversationMapper {

    fun toEntity(dto: ConversationDTO): ConversationEntity

    fun toDto(entity: ConversationEntity): ConversationDTO
}