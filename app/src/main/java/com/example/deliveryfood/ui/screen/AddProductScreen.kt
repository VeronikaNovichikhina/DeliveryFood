package com.example.deliveryfood.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliveryfood.model.Category
import com.example.deliveryfood.viewmodel.ProductViewModel

@Composable
fun AddProductScreen(
    viewModel: ProductViewModel,
    navController: NavController
){
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    var selectedCategory by remember { mutableStateOf(Category.BURGERS) }
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text="Добавить блюдо",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value =  name,
            onValueChange = {name = it},
            label = {Text("Название")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box {

            OutlinedTextField(
                value = selectedCategory.displayName,
                onValueChange = {},
                readOnly = true,
                label =  { Text("Выберите опцию") },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Открыть")
                } },
                modifier = Modifier.clickable { expanded = !expanded }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Category.entries.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.displayName) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value =  desc,
            onValueChange = {desc = it},
            label = {Text("Описание")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = price,
            onValueChange = {price = it},
            label = {Text("Цена")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Ссылка на изображение") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.addProduct(name, price, selectedCategory.displayName,desc, imageUrl)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Сохранить")
        }
    }
}