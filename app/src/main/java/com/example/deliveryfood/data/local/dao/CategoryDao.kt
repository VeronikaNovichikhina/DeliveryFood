package com.example.deliveryfood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliveryfood.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryEntity)
}