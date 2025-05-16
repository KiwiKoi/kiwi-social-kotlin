package com.example.chatterkotlinbackend.dto

class UserDTO {
    var id: String = "exampleId";
    var username: String? = null;
    var email: String = "example@email.com";
    var firstname: String? = null;
    var lastname: String? = null;
    val contacts: List<ContactDTO>? = null;
}