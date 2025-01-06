package com.vsple.salestax_android.models
data class ReceiptItem(
    private val name: String,
    private val quantity: Int,
    private val priceWithTax: Double
) {
    fun getDetails(): String {
        return "$quantity $name: ${"%.2f".format(priceWithTax)}"
    }
}
