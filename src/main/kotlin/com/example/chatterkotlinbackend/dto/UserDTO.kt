package com.example.chatterkotlinbackend.dto

class UserDTO {
    var id: String = "exampleId";
    var username: String? = null;
    var email: String = "example@email.com";
    var firstName: String? = null;
    var lastName: String? = null;
    val contacts: List<ContactDTO>? = null;
}