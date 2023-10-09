package com.fgrutsch.kcfw.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.pattern
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text
import kotlin.math.ceil
import kotlin.math.min

@Composable
fun Pagination(pageNumber: Int, pageSize: Int, total: Int, onPageChange: (Int) -> Unit) {
    val totalPages = ceil(total.toDouble() / pageSize).toInt()
    var pageInput: Int? by mutableStateOf(pageNumber)

    PFPagination(listOf(PFModifier.BOTTOM)) {
        PFOptionsMenu {
            PFOptionsMenuToggleText {
                B { Text("${((pageNumber - 1) * pageSize) + 1} - ${min(pageNumber * pageSize, total)}") }
                Text(" of ")
                B { Text(total.toString()) }
            }
        }

        PFPaginationNav {
            PFPaginationNavControl {
                PFButton(listOf(PFModifier.PLAIN), {
                    if (pageNumber == 1) disabled()
                    onClick { onPageChange(1) }
                }) {
                    PFIcon("fa-angle-double-left")
                }
            }
            PFPaginationNavControl {
                PFButton(listOf(PFModifier.PLAIN), {
                    if (pageNumber == 1) disabled()
                    onClick { onPageChange(pageNumber - 1) }
                }) {
                    PFIcon("fa-angle-left")
                }
            }

            PFPaginationNavPageSelect {
                PFFormControl {
                    Input(InputType.Text) {
                        pattern("[0-9]*")
                        value(pageInput?.toString() ?: "")
                        onInput { pageInput = it.value.toIntOrNull() }
                        onChange { onPageChange(it.value.toIntOrNull() ?: 1) }
                    }
                }
            }

            PFPaginationNavControl {
                PFButton(listOf(PFModifier.PLAIN), {
                    if (pageNumber >= totalPages) disabled()
                    onClick { onPageChange(pageNumber + 1) }
                }) {
                    PFIcon("fa-angle-right")
                }
            }
            PFPaginationNavControl {
                PFButton(listOf(PFModifier.PLAIN), {
                    if (pageNumber >= totalPages) disabled()
                    onClick { onPageChange(totalPages) }
                }) {
                    PFIcon("fa-angle-double-right")
                }
            }
        }
    }
}
