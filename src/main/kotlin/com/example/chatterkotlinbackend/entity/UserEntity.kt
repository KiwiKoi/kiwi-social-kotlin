package com.example.chatterkotlinbackend.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(
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
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
    )
    @JsonBackReference("user-posts")
    var posts: MutableList<PostEntity>? = null,

    @OneToMany(mappedBy = "sender", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JsonBackReference("user-messages")
    var messages: MutableList<MessageEntity>? = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val contacts: MutableSet<ContactEntity> = mutableSetOf(),

    @OneToMany(
        mappedBy = "author",
        orphanRemoval = true
    )
    @JsonBackReference("user-comments")
    var comments: MutableSet<CommentEntity>? = null


) {
    override fun toString(): String {
        return ("UserEntity(id=$id, username=$username, firstname=$firstname, lastname=$lastname, email=$email)")
    }
}