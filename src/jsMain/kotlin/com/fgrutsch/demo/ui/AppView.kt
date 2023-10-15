package com.fgrutsch.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.demo.components.Masthead
import com.fgrutsch.demo.components.Sidebar
import com.fgrutsch.demo.unhash
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun AppView(app: App) {
    WindowLocationHandler(app)

    val navItems = mapOf(
        "Orders" to mapOf(
            "View" to app.ordersRoute,
            "Create" to app.orderRoute,
        ),
        "Preferences" to mapOf(
            "Settings" to app.settingsRoute,
        )
    )

    var sidebarOpen by mutableStateOf(true)

    PFPage {
        Masthead(app.currentUser, sidebarOpen) {
            sidebarOpen = it
        }

        Sidebar(app.currentViewModel, navItems, sidebarOpen)

        PFPageMain {
            PFPageMainSection(listOf(PFModifier.LIGHT)) {
                PFContent {
                    PFTitle(listOf(PFModifier.XL_3)) {
                        Text(app.currentViewModel?.title!!)
                    }
                    P { Text(app.currentViewModel?.description!!) }
                }
            }

            PFDivider()

            PFPageMainSection(listOf(PFModifier.LIGHT, PFModifier.NO_PADDING)) {
                when (val vm = app.currentViewModel) {
                    is HomeViewModel -> HomeView((vm))
                    is OrdersViewModel -> OrdersView(vm)
                    is OrderViewModel -> OrderView(vm)
                    is UserSettingsViewModel -> UserSettingsView(vm)
                }
            }
        }
    }

}


@Composable
private fun WindowLocationHandler(app: App) {
    window.onhashchange = {
        app.onCommand(AppCommand.Navigate(unhash(window.location.hash)))
    }
    if (window.location.hash != "#${app.currentPath}") window.location.hash = app.currentPath
}
