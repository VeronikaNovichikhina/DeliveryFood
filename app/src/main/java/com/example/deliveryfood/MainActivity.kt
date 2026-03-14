package com.example.deliveryfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.deliveryfood.data.local.dao.CategoryDao
import com.example.deliveryfood.data.local.database.AppDatabase
import com.example.deliveryfood.data.local.entity.CategoryEntity
import com.example.deliveryfood.data.repository.ProductRepository
import com.example.deliveryfood.model.Category
import com.example.deliveryfood.ui.screen.MainScreen
import com.example.deliveryfood.ui.theme.DeliveryFoodTheme
import com.example.deliveryfood.viewmodel.AuthViewModel
import com.example.deliveryfood.viewmodel.CartViewModel
import com.example.deliveryfood.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryFoodTheme {
                val viewModel: ProductViewModel = hiltViewModel()
                val cartViewModel: CartViewModel = hiltViewModel()
                val authViewModel : AuthViewModel = hiltViewModel()
                MainScreen(viewModel, cartViewModel, authViewModel)
            }
        }
    }
}
