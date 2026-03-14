package com.example.deliveryfood.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliveryfood.data.local.dao.CartDao
import com.example.deliveryfood.data.local.dao.CategoryDao
import com.example.deliveryfood.data.local.dao.ProductDao
import com.example.deliveryfood.data.local.entity.CartItem
import com.example.deliveryfood.data.local.entity.CategoryEntity
import com.example.deliveryfood.data.local.entity.ProductEntity
import com.example.deliveryfood.model.Category

@Database(entities = [ProductEntity::class, CategoryEntity::class, CartItem::class], version = 5, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun cartItemDao(): CartDao
}