package com.example.chatterkotlinbackend.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime

@Entity
@Table(name = "messages")
data class MessageEntity(
    @NotNull @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotNull
    @JsonProperty("content")
    @Column(nullable = false)
    var content: String,

    @NotNull
    @JsonProperty("sender")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var sender: UserEntity,

    @NotNull
    @JsonProperty("timestamp")
    @Column(nullable = false, updatable = false)
    val timestamp: OffsetDateTime = OffsetDateTime.now(),

    )