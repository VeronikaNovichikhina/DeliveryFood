package com.example.deliveryfood.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val name: String,
    val price: Double,
    val image: String,
    val quantity: Int
)