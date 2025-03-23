package com.example.chatterkotlinbackend.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
@Table(name = "contacts")
data class ContactEntity(
    @Id
    @Column(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user1_id")
    val user1: UserEntity,

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user2_id")
    val user2: UserEntity,

    @Enumerated(EnumType.STRING)
    var status: ContactStatus,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "conversation_id")
    var conversation: ConversationEntity? = null
) {

    enum class ContactStatus {
        PENDING, ACCEPTED, BLOCKED
    }

    override fun toString(): String {
        return ("ContactEntity(id=$id, user1=$user1, user2=$user2,, status=$status, conversation=$conversation)")
    }
}