package com.vsple.salestax_android.viewmodel.logic

import com.vsple.salestax_android.models.ItemDetailsModel
import com.vsple.salestax_android.models.Receipt
import com.vsple.salestax_android.models.ReceiptItem

class ShoppingCart {
    private val items = mutableListOf<ItemDetailsModel>()
    private val taxCalculator = ItemsTaxCalculator()

    /** adding it on add button click in items list*/
    fun addItem(item: ItemDetailsModel) {
        items.add(item)
    }
    /** get items list*/
    fun getItem(): List<ItemDetailsModel> {
        return items
    }
    /** clear all data of list*/
    fun cleaItemList() {
        items.clear()
    }
    /**for implement logic of tax calculation and print item list*/
    fun calculateReceipt(): Receipt {
        var totalSalesTax = 0.0
        var totalPrice = 0.0
        val receiptItems = mutableListOf<ReceiptItem>()

        for (item in items) {

            /**calculate tax 10% basic and 5% imported*/
            val tax = taxCalculator.calculateTax(item)
            val finalPrice = item.price + tax
            totalSalesTax += tax
            totalPrice += finalPrice

            receiptItems.add(
                ReceiptItem(
                    name = item.name,
                    quantity = item.quantity,
                    priceWithTax = finalPrice
                )
            )
        }

        return Receipt(
            items = receiptItems,
            totalSalesTax = totalSalesTax,
            totalPrice = totalPrice
        )
    }
}

