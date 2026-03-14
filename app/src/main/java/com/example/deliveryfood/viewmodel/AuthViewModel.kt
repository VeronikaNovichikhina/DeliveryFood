package com.example.deliveryfood.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _userRole = MutableStateFlow<String?>(null)
    val userRole: StateFlow<String?> = _userRole

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(email: String, password: String) = viewModelScope.launch {
        _errorMessage.value = null
        _loginState.value = false

        try {
            auth.signInWithEmailAndPassword(email.trim(), password.trim()).await()
            val uid = auth.currentUser?.uid ?: throw Exception("User ID not found")

            val adminDoc = firestore.collection("admin").document(uid).get().await()
            _userRole.value = if (adminDoc.exists() && adminDoc.getBoolean("isAdmin") == true) {
                "admin"
            } else {
                val userDoc = firestore.collection("users").document(uid).get().await()
                if (userDoc.exists()) "user"
                else throw Exception("User data not found")
            }

            _loginState.value = true

        } catch (e: Exception) {
            _errorMessage.value = e.localizedMessage
            _loginState.value = false
        }
    }

    fun register(name: String, email: String, password: String) = viewModelScope.launch {
        _errorMessage.value = null
        _loginState.value = false

        if (password.trim().length < 6) {
            _errorMessage.value = "Password must be at least 6 characters"
            return@launch
        }

        try {
            auth.createUserWithEmailAndPassword(email.trim(), password.trim()).await()
            val uid = auth.currentUser?.uid ?: throw Exception("User ID not found")

            val userData = hashMapOf(
                "name" to name,
                "isAdmin" to false
            )

            firestore.collection("users").document(uid).set(userData).await()
            _userRole.value = "user"
            _loginState.value = true

        } catch (e: Exception) {
            _errorMessage.value = e.localizedMessage
            _loginState.value = false
        }
    }

    fun logout() {
        auth.signOut()
        _loginState.value = false
        _userRole.value = null
        _errorMessage.value = null
    }
}