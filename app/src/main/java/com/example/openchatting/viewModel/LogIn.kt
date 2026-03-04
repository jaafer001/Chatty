package com.example.openchatting.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openchatting.utils.FirebaseAuthHelper
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LogIn: ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private val firebaseAuthHelper = FirebaseAuthHelper()

    fun signIn(onSuccess: (FirebaseUser) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Please fill all fields"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            val result = firebaseAuthHelper.signIn(email, password)
            result.onSuccess { user ->
                onSuccess(user)
            }.onFailure { exception ->
                errorMessage = exception.localizedMessage ?: "Sign in failed"
            }

            isLoading = false
        }
    }

    fun clearError() {
        errorMessage = null
    }
}