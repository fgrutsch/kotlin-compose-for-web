package com.fgrutsch.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.webhistory.DefaultWebHistoryController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.fgrutsch.demo.decompose.DefaultRootComponent
import com.fgrutsch.demo.decompose.DetailsComponent
import com.fgrutsch.demo.decompose.ListComponent
import com.fgrutsch.demo.decompose.RootComponent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposableInBody
import web.dom.DocumentVisibilityState
import web.events.EventType


fun main() {
    // include the patternfly resource via JS require
    require("@patternfly/patternfly/patternfly.css")
    require("@patternfly/patternfly/patternfly-addons.css")

//    val orderRepository = InMemoryOrderRepository()
//    val userRepository = InMemoryUserRepository()
//    val app = App(orderRepository, userRepository)
//
//    // Initial navigation when opening the page
//    val path = window.location.hashPath()
//    app.onCommand(AppCommand.Navigate(path))

    val lifecycle = LifecycleRegistry()

    lifecycle.attachToDocument()


    val root = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle),
        webHistoryController = DefaultWebHistoryController()
    )
    renderComposableInBody {
        RootContent(component = root)


//        AppView(app)


    }
}

// Attaches the LifecycleRegistry to the document
private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (web.dom.document.visibilityState == DocumentVisibilityState.visible) {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    web.dom.document.addEventListener(type = EventType("visibilitychange"), callback = { onVisibilityChanged() })
}

@Composable
fun RootContent(component: RootComponent) {
    val childStack by component.childStack.subscribeAsState()

    when (val child = childStack.active.instance) {
        is RootComponent.Child.ListChild -> ListContent(component = child.component)
        is RootComponent.Child.DetailsChild -> DetailsConten(component = child.component)

    }
}

@Composable
fun ListContent(component: ListComponent) {
    val model by component.model.subscribeAsState()

    model.items.forEach { item ->
        Div(
            attrs = {
                onClick { component.onItemClicked(item) }
            }
        ) {
            Text(item)
        }
    }
}

@Composable
fun DetailsConten(component: DetailsComponent) {
    val model by component.model.subscribeAsState()

    Div {
        Text(model.item)
    }
}
