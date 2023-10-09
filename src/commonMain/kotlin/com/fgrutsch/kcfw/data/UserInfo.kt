package com.fgrutsch.kcfw.data

data class UserInfo(
    val email: String,
    val firstName: String,
    val lastName: String,
) {

    val fullName: String = "$firstName $lastName"

}
