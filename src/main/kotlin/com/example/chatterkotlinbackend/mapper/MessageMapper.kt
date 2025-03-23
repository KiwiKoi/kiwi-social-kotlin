package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.MessageDTO
import com.example.chatterkotlinbackend.entity.MessageEntity
import com.example.chatterkotlinbackend.entity.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface MessageMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "sender", source = "sender")
    @Mapping(target = "timestamp", source = "dto.timestamp")
    @Mapping(target = "conversationId", source = "dto.conversationId")
    fun toEntity(dto: MessageDTO, sender: UserEntity): MessageEntity;

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "timestamp", target = "timestamp")
    @Mapping(source = "conversationId", target = "conversationId")
    fun toDto(entity: MessageEntity): MessageDTO;
    fun toDto(entities: List<MessageEntity>): List<MessageDTO>;
}