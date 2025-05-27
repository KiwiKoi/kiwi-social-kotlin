package com.example.chatterkotlinbackend.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class PostDTO(
    var id: String,
    var body: String,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    var createdAt: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    var updatedAt: LocalDateTime? = null,
    var author: UserBasicDTO,
    var published: Boolean = false,
    var favoritedBy: List<String> = ArrayList(),
    var likedByUsers: List<String> = ArrayList(),
    var dislikedByUsers: List<String> = ArrayList(),
)