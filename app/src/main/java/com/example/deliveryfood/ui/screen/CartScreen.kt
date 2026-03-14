package com.example.deliveryfood.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.deliveryfood.ui.components.CartItemCard
import com.example.deliveryfood.viewmodel.CartViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel,
               modifier: Modifier = Modifier){

    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())

    val totalPrice  = cartItems.sumOf { it.price * it.quantity }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Корзина",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(cartItems){item ->
                CartItemCard(
                    item = item,
                    onIncrease = { cartViewModel.increase(item.productId) },
                    onDecrease = { cartViewModel.decrease(item.productId) }
                )
                Spacer(Modifier.height(12.dp))
            }
        }
        Divider()
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Итого:",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "$totalPrice ₸",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Оформить заказ")
        }
    }

}