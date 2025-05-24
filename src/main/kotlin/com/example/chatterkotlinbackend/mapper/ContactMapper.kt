package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.ContactDTO
import com.example.chatterkotlinbackend.entity.ContactEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ContactMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "requester.id", target = "requesterId")
    @Mapping(source = "recipient.id", target = "recipientId")
    @Mapping(source = "requester.username", target = "requesterUsername")
    @Mapping(source = "recipient.username", target = "recipientUsername")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    fun toDTO(entity: ContactEntity): ContactDTO

    fun toDTO(entities: List<ContactEntity>): List<ContactDTO>

}