package com.example.openchatting.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

@Suppress("UNUSED_PARAMETER", "unused")
class SignUp: ViewModel() {
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var isConfirmPasswordVisible by mutableStateOf(false)
}


