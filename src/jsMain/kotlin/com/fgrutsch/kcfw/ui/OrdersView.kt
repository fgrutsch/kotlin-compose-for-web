package com.fgrutsch.kcfw.ui

import androidx.compose.runtime.Composable
import com.fgrutsch.kcfw.components.Dropdown
import com.fgrutsch.kcfw.components.DropdownItem
import com.fgrutsch.kcfw.components.Pagination
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Text

@Composable
fun OrdersView(vm: OrdersViewModel) {
    if (vm.currentPage == null) vm.onCommand(OrdersCommand.QueryPage())

    vm.currentPage?.let { page ->
        val onPageChange = { pageNumber: Int -> vm.onCommand(OrdersCommand.QueryPage(pageNumber)) }

        Pagination(page.pageNumber, page.pageSize, page.total, onPageChange)

        PFDivider { }

        PFTable(listOf(PFModifier.COMPACT)) {
            PFTableHead {
                PFTableRow {
                    PFTableRowHeader { Text("ID") }
                    PFTableRowHeader { Text("Billing Name") }
                    PFTableRowHeader { Text("Date") }
                    PFTableRowHeader { Text("Amount") }
                    PFTableRowHeader { Text("Status") }
                    PFTableRowHeader { Text("Payment Method") }
                    PFTableRowHeader { Text("Product") }
                }
            }

            PFTableBody {
                page.items.forEach {
                    PFTableRow {
                        PFTableRowData { A("#/orders/${it.id}") { Text(it.id.toString()) } }
                        PFTableRowData { Text(it.billingName) }
                        PFTableRowData { Text(it.date) }
                        PFTableRowData { Text("\t\$${it.amount}") }
                        PFTableRowData { Text(it.status) }
                        PFTableRowData { Text(it.paymentMethod) }
                        PFTableRowData { Text(it.product) }
                        PFTableRowDataAction {
                            Dropdown(
                                listOf(
                                    DropdownItem("Delete") { vm.onCommand(OrdersCommand.DeleteOrder(it)) }
                                )
                            )
                        }
                    }
                }
            }
        }

        Pagination(page.pageNumber, page.pageSize, page.total, onPageChange)
    }

}


