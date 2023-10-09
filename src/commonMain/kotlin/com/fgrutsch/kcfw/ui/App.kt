package com.fgrutsch.kcfw.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.compose.Route
import com.fgrutsch.compose.ViewModel
import com.fgrutsch.compose.value
import com.fgrutsch.kcfw.data.OrderRepository
import com.fgrutsch.kcfw.data.UserRepository

class App(private val orderRepository: OrderRepository, private val userRepository: UserRepository) {

    var currentViewModel by mutableStateOf<ViewModel?>(null)
        private set

    var currentPath by mutableStateOf("/")
        private set

    var currentUser by mutableStateOf(userRepository.myUser())
        private set

    private val homeRoute = Route("/") { route, _ -> HomeViewModel(route) }
    val ordersRoute = Route("/orders") { route, _ -> OrdersViewModel(route, orderRepository) }
    val orderRoute = Route("/orders/:id") { route, parts ->
        OrderViewModel(parts.value(":id")?.toLong(), route, this, orderRepository)
    }
    val settingsRoute = Route("/settings") { route, _ -> UserSettingsViewModel(route, this, userRepository) }
    private val allRoutes = listOf(homeRoute, ordersRoute, orderRoute, settingsRoute)

    fun onCommand(cmd: AppCommand) {
        when (cmd) {
            is AppCommand.Navigate -> {
                val path = cmd.path
                val route = allRoutes.find { it.matches(path) } ?: homeRoute
                val dynamicParts = route.dynamicParts(path)
                currentViewModel = route.viewModel(route, dynamicParts)
                currentPath = path
            }

            is AppCommand.UserSettingsChanged -> currentUser = userRepository.myUser()
        }
    }
}

sealed interface AppCommand {
    data class Navigate(val path: String) : AppCommand
    data object UserSettingsChanged : AppCommand
}
