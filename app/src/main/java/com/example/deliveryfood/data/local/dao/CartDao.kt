package com.example.deliveryfood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliveryfood.data.local.entity.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartItem: CartItem)

    @Query("SELECT * FROM cart_item")
    fun getCartItems(): Flow< List<CartItem>>


    @Query("DELETE FROM cart_item WHERE productId =:productId")
    suspend fun removeFromCart(productId: Int)

    @Query("UPDATE cart_item SET quantity = quantity + 1 WHERE productId =:productId")
    suspend fun increaseQuantity(productId: Int)

    @Query("UPDATE cart_item SET quantity = quantity - 1 WHERE productId =:productId")
    suspend fun decreaseQuantity(productId: Int)

    @Query("SELECT * FROM cart_item WHERE productId = :productId LIMIT 1")
    suspend fun getCartItem(productId: Int): CartItem?

}