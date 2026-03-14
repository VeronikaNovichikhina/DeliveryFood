package com.example.deliveryfood.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.deliveryfood.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM products ORDER BY id DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products ORDER BY price ASC")
    fun getProductsSortedNyPrice(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE category = :category")
    fun getProductsByCategory(category: String): Flow<List<ProductEntity>>

    @Query("SELECT DISTINCT category FROM products")
    fun getCategories(): Flow<List<String>>

    @Update
    suspend fun updateProducts(product: ProductEntity)

    @Delete
    suspend fun deleteProducts(product: ProductEntity)
}