package com.fgrutsch.demo.data

import kotlin.random.Random

interface OrderRepository {
    fun create(order: Order): Order
    fun update(order: Order): Order
    fun delete(order: Order)
    fun find(id: Long): Order?
    fun list(page: Int): Page<Order>
}

class InMemoryOrderRepository : OrderRepository {

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
        const val DEFAULT_GENERATED_ORDERS = 45
    }

    private var orders = mutableListOf(*generateRandomOrders(DEFAULT_GENERATED_ORDERS).toTypedArray())

    override fun create(order: Order): Order {
        val autoIncrementedId = orders.maxOf { it.id } + 1
        val newOrder = order.copy(id = autoIncrementedId)
        orders.add(newOrder)
        return newOrder
    }

    override fun update(order: Order): Order {
        orders = orders.map { if (it.id == order.id) order else it }.toMutableList()
        return order
    }

    override fun delete(order: Order) {
        orders.remove(order)
    }

    override fun find(id: Long): Order? = orders.firstOrNull { it.id == id }

    override fun list(page: Int): Page<Order> {
        val total = orders.size
        val offset = (page - 1) * DEFAULT_PAGE_SIZE
        val items = orders.toList().sortedBy { it.date }.reversed().drop(offset).take(DEFAULT_PAGE_SIZE)
        return Page(items, total, page, DEFAULT_PAGE_SIZE)
    }

}


private fun generateRandomOrders(size: Int): List<Order> {
    val firstNames = listOf("Brian", "Alice", "Bob", "John", "Jane", "Mike", "Susan", "Peter", "Mary", "Paul")
    val lastNames = listOf("Smith", "Doe", "Miller", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Davis")
    val statuses = listOf("Paid", "Refunded", "Chargedback")
    val payMethods = listOf("Credit Card", "PayPal", "Bank Transfer")
    val products = listOf("T-Shirt", "Hoodie", "Mug", "Poster", "Sticker", "Book")

    return (1..size).map { id ->
        val billingName = "${firstNames.random()} ${lastNames.random()}"
        val date = {
            val month = Random.nextInt(1, 12).toString().padStart(2, '0')
            val day = Random.nextInt(1, 28).toString().padStart(2, '0')
            "${Random.nextInt(2020, 2023)}-$month-$day"
        }
        val amount = Random.nextLong(10, 1000)
        val status = statuses.random()
        val paymentMethod = payMethods.random()
        val product = products.random()
        Order(id.toLong(), billingName, date(), amount, status, paymentMethod, product)
    }
}

private fun randomName(length: Int): String =
    (1..length).map { ('a'..'z').random() }.joinToString("").replaceFirstChar { it.uppercaseChar() }
