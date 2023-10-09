package com.fgrutsch.kcfw.ui

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.components.PFCard
import com.fgrutsch.pf.components.PFCardBody
import org.jetbrains.compose.web.dom.Text

@Composable
fun HomeView(vm: HomeViewModel) {
    PFCard {
        PFCardBody {
            Text("Demo web application built on top of Compose Multiplatform.")
        }
    }
}
