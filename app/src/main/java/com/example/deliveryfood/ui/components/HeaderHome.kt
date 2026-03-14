package com.example.deliveryfood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HeaderHome() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
            .padding(20.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Добро пожаловать",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Text(
                text = "Что хотите заказать?",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}