package com.fgrutsch.kcfw.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.compose.Route
import com.fgrutsch.compose.ViewModel
import com.fgrutsch.kcfw.data.Order
import com.fgrutsch.kcfw.data.OrderRepository
import com.fgrutsch.kcfw.data.Page

class OrdersViewModel(override val route: Route, private val orderRepository: OrderRepository) : ViewModel {
    override val title: String = "Orders"
    override val description: String = "View and manage customer orders"

    var currentPage by mutableStateOf<Page<Order>?>(null)
        private set


    fun onCommand(cmd: OrdersCommand): Unit = when (cmd) {
        is OrdersCommand.QueryPage -> currentPage = orderRepository.list(cmd.page)
        is OrdersCommand.DeleteOrder -> {
            orderRepository.delete(cmd.order)
            currentPage = orderRepository.list(currentPage?.pageNumber ?: 1)
        }
    }

}

sealed interface OrdersCommand {
    data class QueryPage(val page: Int = 1) : OrdersCommand
    data class DeleteOrder(val order: Order) : OrdersCommand
}
