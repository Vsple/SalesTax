package com.vsple.salestax_android.models

data class ItemDetailsModel(
    private val name: String,
    private val price: Double,
    private val quantity: Int,
    private val isImported: Boolean,
    private val isExempt: Boolean
) {


    fun getName(): String {
        return name
    }


    fun getQuantity(): Int {
        return quantity
    }

    private  fun calculateTax(): Double {
        val basicTax = if (!isExempt) price * 0.10 else 0.0
        val importDuty = if (isImported) price * 0.05 else 0.0
        return Math.ceil((basicTax + importDuty) * 20) / 20.0
    }

    fun getCalculatedTax(): Double {
        return calculateTax()
    }

    private  fun totalPriceWithTax(): Double {
        return (price + calculateTax()) * quantity
    }
    fun getTotalPriceWithTax(): Double {
        return totalPriceWithTax()
    }
}
