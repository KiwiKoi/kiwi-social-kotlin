package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.ContactDTO
import com.example.chatterkotlinbackend.entity.ContactEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ContactMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user1.id", target = "user1Id")
    @Mapping(source = "user2.id", target = "user2Id")
    @Mapping(source = "user1.username", target = "user1Username")
    @Mapping(source = "user2.username", target = "user2Username")
    @Mapping(source = "conversation.id", target = "conversationId")
    fun toDTO(entity: ContactEntity): ContactDTO

    fun toDTO(entities: List<ContactEntity>): List<ContactDTO>

}