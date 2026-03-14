package com.example.deliveryfood.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryfood.data.local.dao.CategoryDao
import com.example.deliveryfood.data.local.entity.ProductEntity
import com.example.deliveryfood.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {
    val categories = repository.getCategories()
    val products = repository.getAllProducts()
    fun addProduct(
        name: String,
        price: String,
        category: String,
        desc: String,
        imageUrl: String
    ){
        viewModelScope.launch {
            val product = ProductEntity(
                name = name,
                price = price.toDoubleOrNull() ?: 0.0,
                category = category,
                desc = desc,
                imageUrl = imageUrl
            )
            repository.addProduct(product)
        }
    }
}