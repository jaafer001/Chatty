package com.example.openchatting.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class SignUp: ViewModel() {
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var isConfirmPasswordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Temporarily disabled Firebase
    // private val firebaseAuthHelper = FirebaseAuthHelper()

    fun signUp(onSuccess: (String) -> Unit) {
        // Validation
        when {
            username.isEmpty() || email.isEmpty() || password.isEmpty() ||
            confirmPassword.isEmpty() || phoneNumber.isEmpty() -> {
                errorMessage = "Please fill all fields"
                return
            }
            password != confirmPassword -> {
                errorMessage = "Passwords do not match"
                return
            }
            password.length < 6 -> {
                errorMessage = "Password must be at least 6 characters"
                return
            }
            !email.contains("@") -> {
                errorMessage = "Invalid email format"
                return
            }
            phoneNumber.length < 10 -> {
                errorMessage = "Phone number must be at least 10 digits"
                return
            }
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            // Temporarily simulate signup
            delay(1000)
            onSuccess(email)

            isLoading = false
        }
    }

    fun clearError() {
        errorMessage = null
    }
}
