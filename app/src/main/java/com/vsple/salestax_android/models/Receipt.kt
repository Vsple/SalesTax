package com.vsple.salestax_android.models


data class Receipt(val items: List<ReceiptItem>, val totalSalesTax: Double, val totalPrice: Double)

