package com.example.deliveryfood.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliveryfood.ui.components.AuthBackground
import com.example.deliveryfood.ui.navigation.Routes
import com.example.deliveryfood.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    val loginState by authViewModel.loginState.collectAsState()
    val userRole by authViewModel.userRole.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(loginState) {
        if (loginState && userRole != null) {
            onRegisterSuccess()
        }
    }
    AuthBackground {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {

                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .widthIn(max = 340.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Создание аккаунта",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Заполните данные",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(24.dp))



                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Имя") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Пароль") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    errorMessage?.let {
                        Text(text = it, color = Color.Red, modifier = Modifier.padding(8.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            authViewModel.register(name, email, password)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Зарегистрироваться")
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Text("Уже есть аккаунт? ")
                        Text(
                            text = "Войти",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                onLoginClick()
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}