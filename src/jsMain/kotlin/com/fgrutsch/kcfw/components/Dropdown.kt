package com.fgrutsch.kcfw.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text

data class DropdownItem(val label: String, val enabled: Boolean = true, val onClick: () -> Unit)

@Composable
fun Dropdown(
    items: List<DropdownItem>,
    toggleContent: @Composable () -> Unit = { PFIcon("fa-ellipsis-v") }
) {
    var showMenu by mutableStateOf(false)

    PFDropdown {
        PFDropdownToggle(
            listOf(PFModifier.PLAIN),
            attrs = { onClick { showMenu = !showMenu } }
        ) {
            toggleContent()
        }

        PFDropdownMenu(
            listOf(PFModifier.ALIGN_RIGHT),
            { if (!showMenu) hidden() }
        ) {
            items.forEach { item ->
                Li {
                    PFDropdownMenuItem(
                        attrs = {
                            if (!item.enabled) disabled()
                            onClick { item.onClick().also { showMenu = false } }
                        }
                    ) {
                        Text(item.label)
                    }
                }
            }
        }
    }


}
