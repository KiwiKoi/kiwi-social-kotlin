package com.example.chatterkotlinbackend.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @Column(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @Column(name = "username")
    var username: String? = null,

    @Column(name = "email", unique = true)
    var email: String,

    @Column(name = "firstname")
    var firstname: String? = null,

    @Column(name = "lastname")
    var lastname: String? = null,

    @OneToMany(
        mappedBy = "author",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
    )
    @JsonBackReference
    var posts: MutableList<PostEntity>? = null,

    @OneToMany(mappedBy = "sender", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JsonBackReference
    var messages: MutableList<MessageEntity>? = mutableListOf(),

    @ManyToMany(mappedBy = "users")
    var conversations: MutableSet<ConversationEntity> = mutableSetOf(),

    @OneToMany(
        mappedBy = "author",
        orphanRemoval = true
    )
    @JsonBackReference
    var comments: MutableSet<CommentEntity>? = null

) {
    override fun toString(): String {
        return ("User(id=$id, username=$username, firstname=$firstname, lastname=$lastname, email=$email)")
    }
}