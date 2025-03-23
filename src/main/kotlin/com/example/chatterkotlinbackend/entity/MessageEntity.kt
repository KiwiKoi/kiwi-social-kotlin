package com.example.chatterkotlinbackend.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime

@Entity
@Table(name = "messages")
data class MessageEntity(
    @Id
    @Column(name = "id")
    var id: String,

    @NotNull
    @JsonProperty("content")
    @Column(nullable = false)
    var content: String,

    @JsonProperty("sender")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    var sender: UserEntity,

    @NotNull
    @JsonProperty("timestamp")
    @Column(nullable = false, updatable = false)
    var timestamp: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "conversation_id")
    var conversationId: String
) {
    override fun toString(): String {
        return ("MessageEntity(id=$id, content=$content, sender=$sender, timestamp=$timestamp, conversationId=$conversationId)")
    }
}