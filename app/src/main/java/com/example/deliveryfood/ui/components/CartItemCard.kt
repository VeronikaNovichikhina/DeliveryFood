package com.example.deliveryfood.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.deliveryfood.data.local.entity.CartItem

@Composable
fun CartItemCard(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${item.price} ₸",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = onDecrease) {
                    Icon(Icons.Default.Remove, null)
                }

                Text(
                    text = item.quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = onIncrease) {
                    Icon(Icons.Default.Add, null)
                }
            }
        }
    }
}