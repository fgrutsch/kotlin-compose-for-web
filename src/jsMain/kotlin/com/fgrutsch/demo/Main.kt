package com.fgrutsch.demo

import com.fgrutsch.demo.data.InMemoryOrderRepository
import com.fgrutsch.demo.data.InMemoryUserRepository
import com.fgrutsch.demo.ui.App
import com.fgrutsch.demo.ui.AppCommand
import com.fgrutsch.demo.ui.AppView
import kotlinx.browser.window
import org.jetbrains.compose.web.renderComposableInBody

fun main() {
    // include the patternfly resource via JS require
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")

    val orderRepository = InMemoryOrderRepository()
    val userRepository = InMemoryUserRepository()
    val app = App(orderRepository, userRepository)

    // Initial navigation when opening the page
    val path = window.location.hashPath()
    app.onCommand(AppCommand.Navigate(path))

    renderComposableInBody {
        AppView(app)
    }
}
