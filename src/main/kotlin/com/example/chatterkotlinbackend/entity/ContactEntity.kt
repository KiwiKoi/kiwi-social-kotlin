package com.example.chatterkotlinbackend.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "contacts")
data class ContactEntity(
    @Id
    @Column(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @NotNull
    @ManyToOne
    @JoinColumn(name = "requester_id")
    val requester: UserEntity,

    @NotNull
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    val recipient: UserEntity,

    @Enumerated(EnumType.STRING)
    var status: ContactStatus = ContactStatus.PENDING,

    val createdAt: OffsetDateTime = OffsetDateTime.now()

) {
    enum class ContactStatus {
        PENDING, ACCEPTED, BLOCKED
    }
}