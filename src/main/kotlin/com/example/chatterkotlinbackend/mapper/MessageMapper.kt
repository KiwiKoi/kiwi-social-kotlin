package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.MessageDTO
import com.example.chatterkotlinbackend.entity.MessageEntity
import com.example.chatterkotlinbackend.entity.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sender", source = "sender")
    @Mapping(target = "content", source = "dto.content")
    fun toEntity(dto: MessageDTO, sender: UserEntity): MessageEntity;

    @Mapping(source = "sender.id", target = "senderId")
    fun toDto(entity: MessageEntity): MessageDTO;
}