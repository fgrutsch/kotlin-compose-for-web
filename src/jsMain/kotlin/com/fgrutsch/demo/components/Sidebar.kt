package com.fgrutsch.demo.components

import androidx.compose.runtime.Composable
import com.fgrutsch.compose.Route
import com.fgrutsch.compose.ViewModel
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.Text

@Composable
fun Sidebar(currentViewModel: ViewModel?, navItems: Map<String, Map<String, Route>>, sidebarOpen: Boolean) {
    PFPageSidebar(if (sidebarOpen) listOf(PFModifier.EXPANDED) else listOf(PFModifier.COLLAPSED)) {
        PFPageSidebarBody {
            PFNav {

                navItems.forEach {
                    PFNavSection {
                        PFNavSectionTitle { Text(it.key) }
                        PFNavList {
                            it.value.forEach { (navLabel, route) ->
                                val isActive = currentViewModel?.route == route
                                PFNavListItem {
                                    PFNavLink(
                                        "#${route.path}",
                                        if (isActive) listOf(PFModifier.CURRENT) else emptyList()
                                    ) {
                                        Text(navLabel)
                                    }
                                }
                            }
                        }
                    }
                }

                PFNavSection {
                    PFNavList {
                        PFNavListItem {
                            PFNavLink("https://www.zendesk.com", attrs = { target(ATarget.Blank) }) {
                                Text("Help Center")
                            }
                        }
                    }
                }

            }
        }
    }
}
