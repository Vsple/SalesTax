package com.vsple.salestax_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vsple.salestax_android.models.ItemDetailsModel
import com.vsple.salestax_android.models.Receipt
import com.vsple.salestax_android.ui.theme.SalesTax_AndroidTheme
import com.vsple.salestax_android.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainVM = hiltViewModel()
            SalesTax_AndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingCartApp(
                        modifier = Modifier.padding(innerPadding),viewModel,
                      )
                }
            }
        }
    }
}
@Composable
fun ShoppingCartApp(modifier : Modifier,viewModel: MainVM) {
    var screenKey = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        ShoppingCartScreen(
            modifier = Modifier,
           viewModel = viewModel,
            onClearAll = {
                screenKey.value++ // Change the key to trigger recomposition
            },
            key = screenKey.value // Pass the key to recompose ShoppingCartScreen
        )
    }
}



@Composable
fun ShoppingCartScreen(modifier: Modifier,
                       viewModel: MainVM,
                       onClearAll: () -> Unit,
                       key: Int ) {

    val receiptFinal = viewModel.receipt.collectAsState<Receipt?, Receipt?>(initial = null)
    val items = remember { mutableStateListOf<ItemDetailsModel>() }



 //   val cart = remember { ShoppingCart() }
    var name = remember { mutableStateOf("") }
    var price = remember { mutableStateOf("") }
    var quantity = remember { mutableStateOf("") }
    var isImported = remember { mutableStateOf(false) }
    var isExempt = remember { mutableStateOf(false) }
    var receipt = remember { mutableStateOf<Receipt?>(null) }
    var showExemptedCheckbox = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val maxCharName = 18
    val maxCharPrice = 7
    fun updateExemptedState(name: String) {
        val exemptCategories = listOf("book", "chocolates","chocolate","chocolate bar","pills", "medicine", "food","imported chocolates", "headache pills")
        val isInExemptCategory = exemptCategories.any { name.contains(it, ignoreCase = true) }
        isExempt.value = isInExemptCategory
        showExemptedCheckbox.value = isInExemptCategory
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        TextField(
            value = name.value,
            onValueChange = {
                if (it.length <= maxCharName) name.value = it

                    updateExemptedState(it)

            },
            label = { Text("Item Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = price.value,
            onValueChange = {
                if (it.length <= maxCharPrice) price.value = it
                            },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = quantity.value,
            onValueChange = {
                if (it.length <= maxCharPrice) quantity.value = it
                            },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f)
            ) {
                Checkbox(checked = isImported.value, onCheckedChange = { isImported.value = it

                    updateExemptedState( name.value)})
                Text("Imported")
            }
            // Add Item Button
            Button(
                onClick = {
                    if (name.value.isNotBlank() && price.value.isNotBlank() && quantity.value.isNotBlank()) {
                        viewModel.addItem(
                            ItemDetailsModel(
                                name = name.value,
                                price = price.value.toDoubleOrNull() ?: 0.0,
                                quantity = quantity.value.toIntOrNull() ?: 1,
                                isImported = isImported.value,
                                isExempt = isExempt.value
                            )
                        )
                        name.value = ""
                        price.value = ""
                        quantity.value = ""
                        isImported.value = false
                        isExempt.value = false
                    }else{
                        Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f)
            ) {
                Text( text = AnnotatedString("Add Item"),
                    modifier = Modifier,
                    color = Color.White)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            // Show the Exempted Checkbox if showExemptedCheckbox.value is true
            if (showExemptedCheckbox.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isExempt.value,
                        onCheckedChange = {},
                        enabled = false // Checkbox is not clickable
                    )
                    Text("Exempted")
                }
            }else{
                Spacer(modifier = Modifier.weight(.5f))
            }
            Button(
                onClick = {
                    viewModel.clearCart()
                    // Clear all state variables
                    name.value = ""
                    price.value = ""
                    quantity.value = ""
                    isImported.value = false
                    isExempt.value = false
                    showExemptedCheckbox.value = false
                    receipt.value = null

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f),
                colors = ButtonDefaults.buttonColors(Color.Gray),

            ) {
                Text(
                    text = AnnotatedString("Clear Item"),
                    modifier = Modifier,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(28.dp))

        // Generate Receipt Button
        Button(
            onClick = {
                keyboardController?.hide()
                if (viewModel.getItems().isNotEmpty()) {
                    viewModel.generateReceipt()
                } else {
                    Toast.makeText(context, "Please add items to the cart", Toast.LENGTH_SHORT).show()
                }
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text( text = AnnotatedString("Generate Receipt"),
                modifier = Modifier,
                color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

       /* // Display Receipt
        receipt.value?.let {
            Text("Receipt:", style = MaterialTheme.typography.h6)
            Divider()

            Text("${ it.printReceipt()}")
            Spacer(modifier = Modifier.height(8.dp))

            Text("Sales Taxes: ${"%.2f".format(it.getTotalSalesTax() ?: 0.0)}")
           Text("Total: ${"%.2f".format(it.getTotalPrice())}")

        }*/
        // Display Receipt
        receipt?.let {
            Text("Receipt:", style = MaterialTheme.typography.h6)
            Divider()
            val it = receiptFinal.value
            Text(it?.printReceipt()?: "")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Sales Taxes: ${"%.2f".format(it?.getTotalSalesTax() ?: 0.0)}")
            Text("Total: ${"%.2f".format(it?.getTotalPrice()?: 0.0)}")
        }

    }

}
/*fun main() {
    val shoppingCart = ShoppingCart()

    println("Enter the items in your shopping basket (type 'done' to finish):")
    while (true) {
        print("Enter item (format: quantity name price imported(true/false) exempt(true/false)): ")
        val input = readLine()

        if (input == "done") break

        val parts = input?.split(" ") ?: continue
        if (parts.size != 5) {
            println("Invalid input format! Please try again.")
            continue
        }

        try {
            val quantity = parts[0].toInt()
            val name = parts[1]
            val price = parts[2].toDouble()
            val isImported = parts[3].toBoolean()
            val isExempt = parts[4].toBoolean()

            val item = ItemDetailsModel(name, price, quantity, isImported, isExempt)
            shoppingCart.addItem(item)
        } catch (e: Exception) {
            println("Error parsing input. Please try again.")
        }
    }

    println("\nYour Receipt:")
    val receipt = shoppingCart.getGenerateReceipt()
    println(receipt.printReceipt())
}*/
