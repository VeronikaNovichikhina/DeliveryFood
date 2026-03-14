package com.example.deliveryfood.data.local.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.deliveryfood.data.local.dao.CategoryDao
import com.example.deliveryfood.data.local.entity.CategoryEntity
import com.example.deliveryfood.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatabaseCallback : RoomDatabase.Callback() {

    fun seed(database: AppDatabase) {

        CoroutineScope(Dispatchers.IO).launch {

            val categoryDao = database.categoryDao()

            Category.entries.forEach {
                categoryDao.insert(
                    CategoryEntity(it.displayName)
                )
            }

        }
    }
}