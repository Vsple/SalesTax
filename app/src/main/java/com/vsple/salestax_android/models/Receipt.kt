package com.vsple.salestax_android.models


class Receipt(private val items: List<ReceiptItem>, private val totalSalesTax: Double, private val totalPrice: Double) {

    fun printReceipt(): String {
        val itemDetails = items.joinToString("\n") { it.getDetails() }
        return """
            $itemDetails
            Sales Taxes: ${"%.2f".format(totalSalesTax)}
            Total: ${"%.2f".format(totalPrice)}
        """.trimIndent()
    }

    fun getTotalSalesTax(): Double {
      return  totalSalesTax
    }

    fun getTotalPrice() : Double{
       return totalPrice
    }
}

