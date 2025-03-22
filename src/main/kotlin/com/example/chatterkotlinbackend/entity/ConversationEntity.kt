package com.example.chatterkotlinbackend.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "conversations")
data class ConversationEntity (
    @Id
    @Column(name = "id") var id: String = UUID.randomUUID().toString(),

    @ManyToMany
    var users: MutableSet<UserEntity> = mutableSetOf(),

    @OneToMany
    var messages: MutableSet<MessageEntity> = mutableSetOf(),
){}