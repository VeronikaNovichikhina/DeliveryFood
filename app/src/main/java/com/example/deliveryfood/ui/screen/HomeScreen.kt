package com.example.deliveryfood.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deliveryfood.viewmodel.ProductViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.Navigator
import com.example.deliveryfood.ui.components.CategoryChip
import com.example.deliveryfood.ui.components.FoodCard
import com.example.deliveryfood.ui.components.HeaderHome
import com.example.deliveryfood.viewmodel.CartViewModel

@Composable
fun HomeScreen(
    viewModel: ProductViewModel,
    cartViewModel: CartViewModel){

    val products by viewModel.products.collectAsState(initial = emptyList())

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Все") }
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val categoryNames = listOf("Все") + categories.map { it.name }
    val filteredProducts = products
        .filter {
            it.name.contains(searchQuery, true)
        }
        .filter { selectedCategory == "Все" || it.category == selectedCategory }

    val listState = rememberLazyListState()
    val collapseFraction = minOf(
        1f,
        listState.firstVisibleItemScrollOffset / 300f
    )

    val headerHeight by animateDpAsState(
        targetValue = (120 * (1 - collapseFraction)).dp,
        label = ""
    )

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            Box(
                modifier = Modifier
                    .height(headerHeight)
                    .padding(8.dp)
            ) {
                HeaderHome()
            }
        }
        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Поиск еды...") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(categoryNames) { category ->
                        CategoryChip(
                            category = category,
                            selected = selectedCategory == category
                        ) {
                            selectedCategory = category
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        items(filteredProducts) { product ->
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                FoodCard(product, onAddToCart = {
                    cartViewModel.addToCart(it)
                })
            }
        }
    }
}