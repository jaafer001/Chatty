package com.example.openchatting.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class LogIn: ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Temporarily disabled Firebase
    // private val firebaseAuthHelper = FirebaseAuthHelper()

    fun signIn(onSuccess: (String) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Please fill all fields"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            // Temporarily simulate login
            delay(1000)
            onSuccess(email)

            isLoading = false
        }
    }

    fun clearError() {
        errorMessage = null
    }
}