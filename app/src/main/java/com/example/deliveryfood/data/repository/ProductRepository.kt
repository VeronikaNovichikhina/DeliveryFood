package com.example.deliveryfood.data.repository

import com.example.deliveryfood.data.local.dao.CategoryDao
import com.example.deliveryfood.data.local.dao.ProductDao
import com.example.deliveryfood.data.local.entity.CategoryEntity
import com.example.deliveryfood.data.local.entity.ProductEntity
import com.example.deliveryfood.model.Category
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao
) {
    fun getAllProducts() = productDao.getAllProducts()
    fun getCategories() = categoryDao.getCategories()

    suspend  fun addProduct(product: ProductEntity){
        productDao.insertProduct(product)

    }


}