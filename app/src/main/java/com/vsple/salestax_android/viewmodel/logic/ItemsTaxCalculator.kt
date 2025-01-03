package com.vsple.salestax_android.viewmodel.logic

import com.vsple.salestax_android.models.ItemDetailsModel


class ItemsTaxCalculator {


    fun calculateTax(item: ItemDetailsModel): Double {
        var tax = 0.0

        /*
        Apply basic sales tax only if the item is not exempt
         */
        if (!item.isExempt) {
            tax += item.price * 0.10  // 10% basic sales tax
        }

        /*
          Apply import duty if the item is imported
          5% import duty
         */

        if (item.isImported) {
            tax += item.price * 0.05
        }

        /*
          Round up to nearest 0.05
        */

        return Math.ceil(tax * 20) / 20.0
    }


}