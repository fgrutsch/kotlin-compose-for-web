package com.fgrutsch.demo.data

data class UserInfo(
    val email: String,
    val firstName: String,
    val lastName: String,
) {

    val fullName: String = "$firstName $lastName"

}
