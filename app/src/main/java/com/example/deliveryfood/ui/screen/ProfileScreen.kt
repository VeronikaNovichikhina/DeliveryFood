package com.example.deliveryfood.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliveryfood.ui.components.AuthBackground
import com.example.deliveryfood.ui.navigation.Routes
import com.example.deliveryfood.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val userRole by authViewModel.userRole.collectAsState()

    AuthBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "U",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Гость",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (userRole == "admin") {
                        ActionButton(text = "Добавить блюдо") {
                            navController.navigate(Routes.ADD_PRODUCT_ADMIN)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    } else {
                        ActionButton(text = "Мои заказы") {
                            // navController.navigate(Routes.MY_ORDERS)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton(text = "Настройки") {
                            // navController.navigate(Routes.SETTINGS)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    ActionButton(text = "Выйти", isDestructive = true) {
                        authViewModel.logout()
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    isDestructive: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isDestructive) Color(0xFFFF5252) else MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}