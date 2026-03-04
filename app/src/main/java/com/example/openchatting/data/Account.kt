package com.example.openchatting.data

import androidx.compose.ui.graphics.painter.Painter

data class Account(
    val name: String,
    val profilePicture: Int,
    val lastMessage: String,
    val lastMessageTime: String,
)
