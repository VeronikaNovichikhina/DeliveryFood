package com.example.deliveryfood.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(
    category: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        color = if (selected)
            MaterialTheme.colorScheme.primary
        else
            Color.LightGray,
        modifier = Modifier
            .padding(end = 8.dp)
    ) {

        Text(
            text = category,
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
            color = if (selected) Color.White else Color.Black
        )
    }
}