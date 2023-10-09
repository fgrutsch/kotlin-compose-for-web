package com.fgrutsch.kcfw.components

import androidx.compose.runtime.Composable
import com.fgrutsch.kcfw.data.UserInfo
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.PFUtil
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.type
import org.jetbrains.compose.web.dom.Text

@Composable
fun Masthead(currentUser: UserInfo, sidebarOpen: Boolean, onToggle: (Boolean) -> Unit) {
    PFMasthead {
        PFMastheadToggle {
            PFButton(
                classes = listOf(PFModifier.PLAIN),
                attrs = {
                    type(ButtonType.Button)
                    onClick { onToggle(!sidebarOpen) }
                }
            ) { PFIcon("fa-bars") }
        }

        PFMastheadMain {
            PFMastheadBrand {
                PFMastheadBrandImg("/assets/brand.png")
            }
        }

        PFMastheadContent {
            PFToolbar {
                PFToolbarContent {
                    PFToolbarContentSection {
                        PFToolbarItem(listOf(PFModifier.ALIGN_RIGHT)) {
                            Dropdown(
                                items = listOf(
                                    DropdownItem(currentUser.fullName, enabled = false) { },
                                    DropdownItem("Logout") { }
                                ),
                                toggleContent = {
                                    PFButton(
                                        classes = listOf(PFModifier.PLAIN),
                                        attrs = {
                                            type(ButtonType.Button)
                                        }
                                    ) {
                                        PFIcon("fa-user", listOf(PFUtil.PR_MD))
                                        Text(currentUser.email)
                                        PFDropdownToggleIcon {
                                            PFIcon("fa-caret-down")
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
