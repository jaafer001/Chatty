package com.example.openchatting.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LogIn: ViewModel() {
    var email by  mutableStateOf("")
    val password by mutableStateOf("")
    val isPasswordVisible by mutableStateOf(false)
}