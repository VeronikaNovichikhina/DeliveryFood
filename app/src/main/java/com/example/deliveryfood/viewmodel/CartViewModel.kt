package com.example.deliveryfood.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryfood.data.local.entity.ProductEntity
import com.example.deliveryfood.data.repository.CartItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartItemRepository
) : ViewModel(){
    val cartItems = repository.cartItems

    fun addToCart(product: ProductEntity){
        viewModelScope.launch {
            repository.add(product)
        }
    }
    fun increase(productId: Int){
        viewModelScope.launch {
            repository.increase(productId)
        }
    }
    fun decrease(productId: Int){
        viewModelScope.launch {
            repository.decrease(productId)
        }
    }
}