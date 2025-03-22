package com.example.chatterkotlinbackend.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity
@Table(name = "comments")
data class CommentEntity(
    @Id @Column(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @Column(name = "body")
    var body: String,

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = null,

    @JsonIgnoreProperties("comments") @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JsonManagedReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var author: UserEntity,

    @JsonIgnoreProperties("comments") @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JsonManagedReference
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    var post: PostEntity
    
) {

    override fun toString(): String {
        return ("Comment(id=$id, body=$body, createdAt=$createdAt, updatedAt=$updatedAt, author=$author)")
    }

    class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext): LocalDateTime {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
            return LocalDateTime.parse(parser.valueAsString, formatter)
        }
    }
}
