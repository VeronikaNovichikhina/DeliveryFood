package com.example.deliveryfood.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.deliveryfood.ui.navigation.Routes
import com.example.deliveryfood.viewmodel.AuthViewModel
import com.example.deliveryfood.viewmodel.CartViewModel
import com.example.deliveryfood.viewmodel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    val userRole by authViewModel.userRole.collectAsState()
    val loginState by authViewModel.loginState.collectAsState()

    val startDestination = when {
        loginState && userRole == "admin" -> Routes.ADD_PRODUCT_ADMIN
        loginState && userRole == "user" -> Routes.HOME
        else -> Routes.LOGIN
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.LOGIN) {
            LoginScreen(
                authViewModel = authViewModel,
                navController = navController,
                onLoginSuccess = {
                    val route = when (authViewModel.userRole.value) {
                        "admin" -> Routes.PROFILE
                        else -> Routes.HOME
                    }
                    navController.navigate(route) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                authViewModel = authViewModel,
                navController = navController,
                onRegisterSuccess = {
                    val route = when (authViewModel.userRole.value) {
                        "admin" -> Routes.PROFILE
                        else -> Routes.HOME
                    }
                    navController.navigate(route) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable(Routes.HOME) {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
                HomeScreen(viewModel, cartViewModel)
            }
        }

        composable(Routes.CART) {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
                CartScreen(
                    cartViewModel = cartViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(Routes.PROFILE) {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
                ProfileScreen(navController, authViewModel)
            }
        }

        composable(Routes.ADD_PRODUCT_ADMIN) {
            if (userRole == "admin") {
                AddProductScreen(viewModel, navController)
            } else {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }
        }

        composable(Routes.PRODUCT_DETAIL) {

        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") {
                    navController.navigate("home") {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Главная") }
        )
        NavigationBarItem(
            selected = currentRoute == "cart",
            onClick = {
                if (currentRoute != "cart") {
                    navController.navigate("cart") {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Коризна") }
        )
        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = {
                if (currentRoute != "profile") {
                    navController.navigate("profile") {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Профиль") }
        )
    }
}