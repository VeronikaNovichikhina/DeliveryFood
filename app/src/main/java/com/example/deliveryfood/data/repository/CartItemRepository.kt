package com.example.deliveryfood.data.repository

import com.example.deliveryfood.data.local.dao.CartDao
import com.example.deliveryfood.data.local.entity.CartItem
import com.example.deliveryfood.data.local.entity.ProductEntity

class CartItemRepository(private val cartDao: CartDao) {
    val cartItems = cartDao.getCartItems()

    suspend fun add(product: ProductEntity){
        val existingItem = cartDao.getCartItem(product.id)
        if(existingItem == null){
            cartDao.addToCart(
                CartItem(
                    productId = product.id,
                    name =  product.name,
                    price = product.price,
                    image = product.imageUrl,
                    quantity = 1
            ))
        }else{
            cartDao.increaseQuantity(product.id)
        }
    }
    suspend fun increase(productId: Int){
        cartDao.increaseQuantity(productId)
    }
    suspend fun decrease(productId: Int){
        val item = cartDao.getCartItem(productId)

        if(item != null){
            if(item.quantity > 1){
                cartDao.decreaseQuantity(productId)
            }else{
                cartDao.removeFromCart(productId)
            }
        }
    }
}