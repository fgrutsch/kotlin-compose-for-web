package com.fgrutsch.demo.ui

import com.fgrutsch.compose.Route
import com.fgrutsch.compose.ViewModel

class HomeViewModel(override val route: Route) : ViewModel {
    override val title: String = "Kotlin Compose for Web"
    override val description: String = "v0.1.0"
}
