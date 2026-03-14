package com.example.deliveryfood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.deliveryfood.data.local.entity.ProductEntity

@Composable
fun FoodCard(product: ProductEntity,
             onAddToCart: (ProductEntity) -> Unit)
{

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column {

            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(180.dp)
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = product.desc,
                    maxLines = 2,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${product.price} ₸",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Button(
                        onClick = {
                            onAddToCart(product)
                        },
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("В корзину")
                    }
                }
            }
        }
    }
}