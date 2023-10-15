package com.fgrutsch.demo

import com.fgrutsch.demo.data.InMemoryOrderRepository
import com.fgrutsch.demo.data.InMemoryUserRepository
import com.fgrutsch.demo.ui.App
import com.fgrutsch.demo.ui.AppCommand
import com.fgrutsch.demo.ui.AppView
import kotlinx.browser.window
import org.jetbrains.compose.web.renderComposableInBody

/**
 * Example on how to bridge to existing JS interfaces
 */
external fun require(module: String): dynamic

fun main() {
    // include the patternfly resource via normal js require
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")

    val orderRepository = InMemoryOrderRepository()
    val userRepository = InMemoryUserRepository()
    val app = App(orderRepository, userRepository)

    // Initial navigation when opening the page
    val to = unhash(window.location.hash).ifEmpty { "/" }
    app.onCommand(AppCommand.Navigate(to))

    renderComposableInBody {
        AppView(app)
    }
}
