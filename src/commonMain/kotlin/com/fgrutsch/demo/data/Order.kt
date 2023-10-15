package com.fgrutsch.demo.data

data class Order(
    val id: Long,
    val billingName: String,
    val date: String,
    val amount: Long,
    val status: String,
    val paymentMethod: String,
    val product: String
) {

    companion object {
        val UNINITIALIZED = Order(-1, "", "", 0, "", "", "")
    }

}
