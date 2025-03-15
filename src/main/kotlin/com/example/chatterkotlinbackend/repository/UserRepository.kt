package com.example.chatterkotlinbackend.repository

import com.example.chatterkotlinbackend.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface  UserRepository : JpaRepository<UserEntity, String> {
}