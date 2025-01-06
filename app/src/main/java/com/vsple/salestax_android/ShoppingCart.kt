package com.vsple.salestax_android

import com.vsple.salestax_android.models.ItemDetailsModel
import com.vsple.salestax_android.models.Receipt
import com.vsple.salestax_android.models.ReceiptItem

class ShoppingCart {
    private val items = mutableListOf<ItemDetailsModel>()

    fun addItem(item: ItemDetailsModel) {
        items.add(item)
    }

    fun clearCart() {
        items.clear()
    }

    fun getItems(): List<ItemDetailsModel> {
        return items
    }

  private  fun generateReceipt(): Receipt {
        val receiptItems = mutableListOf<ReceiptItem>()
        var totalSalesTax = 0.0
        var totalPrice = 0.0

        for (item in items) {
            val tax = item.getCalculatedTax()
            val totalItemPriceWithTax = item.getTotalPriceWithTax()
            totalSalesTax += tax * item.getQuantity()
            totalPrice += totalItemPriceWithTax

            receiptItems.add(
                ReceiptItem(
                    name = item.getName(),
                    quantity = item.getQuantity(),
                    priceWithTax = totalItemPriceWithTax
                )
            )
        }

        return Receipt(receiptItems, totalSalesTax, totalPrice)
    }
    fun getGenerateReceipt(): Receipt {
        return generateReceipt()
    }
}


