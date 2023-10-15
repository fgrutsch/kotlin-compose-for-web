package com.fgrutsch.demo.ui

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.compose.Route
import com.fgrutsch.compose.ViewModel
import com.fgrutsch.demo.data.UserInfo
import com.fgrutsch.demo.data.UserRepository

class UserSettingsViewModel(
    override val route: Route,
    private val app: App,
    private val userRepository: UserRepository
) : ViewModel {
    override val title: String = "Settings"
    override val description: String = "Manage settings for your account."

    var state by mutableStateOf<SettingsState>(SettingsState.Init)
        private set

    private var originalEntity: UserInfo? by mutableStateOf(null)

    var formEntity: UserInfo? by mutableStateOf(originalEntity)
        private set

    val entityChanged by derivedStateOf { originalEntity != formEntity }

    fun onCommand(cmd: SettingsCommand): Unit = when (cmd) {
        is SettingsCommand.Get -> {
            originalEntity = userRepository.myUser()
            formEntity = originalEntity
            state = SettingsState.Loaded
        }

        is SettingsCommand.Update -> {
            userRepository.update(formEntity!!)
            onCommand(SettingsCommand.Get)
            app.onCommand(AppCommand.UserSettingsChanged)
        }

        is SettingsCommand.Revert -> {
            formEntity = originalEntity
        }

        is SettingsCommand.MutateEntity -> {
            formEntity = cmd.fn(formEntity!!)
        }
    }

}


sealed interface SettingsState {
    data object Init : SettingsState
    data object Loaded : SettingsState
}

sealed interface SettingsCommand {
    data object Get : SettingsCommand
    data object Update : SettingsCommand
    data object Revert : SettingsCommand
    data class MutateEntity(val fn: (UserInfo).() -> UserInfo) : SettingsCommand
}
