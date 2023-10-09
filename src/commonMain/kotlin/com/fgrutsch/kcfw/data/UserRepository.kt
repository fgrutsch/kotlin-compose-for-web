package com.fgrutsch.kcfw.data

interface UserRepository {
    fun myUser(): UserInfo
    fun update(user: UserInfo): UserInfo
}


class InMemoryUserRepository : UserRepository {

    private var fakeUser: UserInfo = UserInfo("grutsch@lovelysystems.com", "Fabian", "Grutsch")

    override fun myUser(): UserInfo = fakeUser

    override fun update(user: UserInfo): UserInfo {
        fakeUser = user
        return user
    }

}
