package com.vsple.salestax_android.models

data class ItemDetailsModel(
    val name: String,
    val price: Double,
    val quantity: Int,
    val isImported: Boolean,
    val isExempt: Boolean
)
