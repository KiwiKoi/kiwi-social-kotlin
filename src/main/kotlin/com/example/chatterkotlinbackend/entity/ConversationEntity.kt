package com.example.chatterkotlinbackend.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "conversations")
data class ConversationEntity(
    @Id
    @Column(name = "id") var id: String = UUID.randomUUID().toString(),

    @OneToMany(
        fetch = FetchType.EAGER,
        cascade = [(CascadeType.ALL)],
        orphanRemoval = true
    )
    @JoinColumn(name = "conversation_id")
    var messages: MutableSet<MessageEntity> = mutableSetOf(),

    @Column(name = "contact_id")
    var contactId: String
) {}