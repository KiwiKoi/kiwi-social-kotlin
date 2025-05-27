package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.PostDTO
import com.example.chatterkotlinbackend.entity.PostEntity
import com.example.chatterkotlinbackend.entity.UserEntity
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
abstract class PostMapper {

    @Mapping(target = "favoritedBy", ignore = true)
    @Mapping(target = "likedByUsers", ignore = true)
    @Mapping(target = "dislikedByUsers", ignore = true)
    abstract fun toEntity(dto: PostDTO): PostEntity;

    @Mapping(source = "favoritedBy", target = "favoritedBy", qualifiedByName = ["userToIdList"])
    @Mapping(source = "likedByUsers", target = "likedByUsers", qualifiedByName = ["userToIdList"])
    @Mapping(source = "dislikedByUsers", target = "dislikedByUsers", qualifiedByName = ["userToIdList"])
    abstract fun toDto(entity: PostEntity): PostDTO;
    abstract fun toDto(entities: List<PostEntity>): List<PostDTO>;

    @Named("userToId")
    fun userToId(user: UserEntity): String {
        return user.id
    }

    @Named("userToIdList")
    @IterableMapping(qualifiedByName = ["userToId"])
    fun userToIdList(users: List<UserEntity>): List<String> = users.map { userToId(it)}
}