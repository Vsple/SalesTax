package com.vsple.salestax_android.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vsple.salestax_android.ShoppingCart
import com.vsple.salestax_android.models.ItemDetailsModel
import com.vsple.salestax_android.models.Receipt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor() : ViewModel() {
    private val _cart = ShoppingCart()
    private val _receipt = MutableStateFlow<Receipt?>(null)
    val receipt: StateFlow<Receipt?> = _receipt

    fun addItem(item: ItemDetailsModel) {
        _cart.addItem(item)
    }

    fun clearCart() {
        _cart.clearCart()
        _receipt.value = null
    }

    fun generateReceipt() {
        _receipt.value = _cart.getGenerateReceipt()
    }

    fun getItems(): List<ItemDetailsModel> {
        return _cart.getItems()
    }
}

