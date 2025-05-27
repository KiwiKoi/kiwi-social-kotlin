package com.example.chatterkotlinbackend.mapper

import com.example.chatterkotlinbackend.dto.UserBasicDTO
import com.example.chatterkotlinbackend.dto.UserDTO
import com.example.chatterkotlinbackend.entity.PostEntity
import com.example.chatterkotlinbackend.entity.UserEntity
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
abstract class UserMapper {

    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "likedPosts", ignore = true)
    @Mapping(target = "dislikedPosts", ignore = true)
    abstract fun toEntity(dto: UserDTO): UserEntity;

    @Mapping(source = "favorites", target = "favorites", qualifiedByName = ["postToIdList"])
    @Mapping(source = "likedPosts", target = "likedPosts", qualifiedByName = ["postToIdList"])
    @Mapping(source = "dislikedPosts", target = "dislikedPosts", qualifiedByName = ["postToIdList"])
    abstract fun toDto(entity: UserEntity): UserDTO
    abstract fun toDto(entities: List<UserEntity>): List<UserDTO>

    abstract fun toBasicDto(entity: UserEntity): UserBasicDTO
    abstract fun toBasicDto(entities: List<UserEntity>): List<UserBasicDTO>

    @Named("postToId")
    fun postToId(post: PostEntity): String {
        return post.id
    }

    @Named("postToIdList")
    @IterableMapping(qualifiedByName = ["postToId"])
    fun postToIdList(posts: Set<PostEntity>): List<String> = posts.map { postToId(it) }
}