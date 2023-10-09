package com.fgrutsch.kcfw.ui

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fgrutsch.compose.Route
import com.fgrutsch.compose.ViewModel
import com.fgrutsch.kcfw.data.Order
import com.fgrutsch.kcfw.data.OrderRepository

class OrderViewModel(
    val id: Long? = null,
    override val route: Route,
    private val app: App,
    private val orderRepository: OrderRepository
) : ViewModel {

    override val title: String get() = if (id == null) "Create" else "Update"

    override val description: String
        get() = id?.let { "Updating customer order $it" } ?: "Creating a new customer order"

    var state: OrderState by mutableStateOf(OrderState.Init)
        private set

    private var originalEntity: Order? by mutableStateOf(null)

    var formEntity: Order? by mutableStateOf(originalEntity)
        private set

    val entityChanged by derivedStateOf { originalEntity != formEntity }

    fun onCommand(cmd: OrderCommand): Unit = when (cmd) {
        is OrderCommand.Get -> {
            originalEntity = (if (id == null) Order.UNINITIALIZED else orderRepository.find(id)!!)
            formEntity = originalEntity
            state = OrderState.Loaded
        }

        is OrderCommand.Save -> {
            val savedEntity = if (id == null) {
                orderRepository.create(formEntity!!)
            } else {
                orderRepository.update(formEntity!!)
            }
            app.onCommand(AppCommand.Navigate("/orders/${savedEntity.id}"))
        }

        is OrderCommand.Revert -> {
            formEntity = originalEntity
        }

        is OrderCommand.MutateEntity -> {
            formEntity = cmd.fn(formEntity!!)
        }
    }
}

sealed interface OrderState {
    data object Init : OrderState
    data object Loaded : OrderState
}

sealed interface OrderCommand {
    data object Get : OrderCommand
    data object Save : OrderCommand
    data object Revert : OrderCommand
    data class MutateEntity(val fn: (Order).() -> Order) : OrderCommand
}
