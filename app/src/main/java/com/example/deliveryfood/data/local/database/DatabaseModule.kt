package com.example.deliveryfood.data.local.database

import android.content.Context
import androidx.room.Room
import com.example.deliveryfood.data.local.dao.CartDao
import com.example.deliveryfood.data.local.dao.CategoryDao
import com.example.deliveryfood.data.local.dao.ProductDao
import com.example.deliveryfood.data.repository.CartItemRepository
import com.example.deliveryfood.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase{

        val callback = DatabaseCallback()

        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "food_delivery_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        callback.seed(db)

        return db
    }

    @Provides
    fun provideProductDao(
        database: AppDatabase
    ): ProductDao{
        return database.productDao()
    }
    @Provides
    fun provideCategoryDao(
        database: AppDatabase
    ): CategoryDao{
        return database.categoryDao()
    }

    @Provides
    fun cartItemDao(database: AppDatabase): CartDao{
        return database.cartItemDao()
    }
    @Provides
    @Singleton
    fun provideRepository(
        categoryDao: CategoryDao,
        productDao: ProductDao
    ): ProductRepository{
        return ProductRepository(productDao,categoryDao)
    }

    @Provides
    @Singleton
    fun cartItemRepository(
        cartItemDao: CartDao
    ): CartItemRepository{
        return CartItemRepository(cartItemDao)
    }
}