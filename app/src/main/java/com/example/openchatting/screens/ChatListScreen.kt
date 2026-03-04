package com.example.openchatting.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatListScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "ChatListScreen",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
fun ChatMessageCard(modifier: Modifier = Modifier) {

}

@Suppress("UNUSED_PARAMETER")
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp",
)
@Composable
fun ChatListScreenPreview() {
    Scaffold() {
        ChatMessageCard(modifier = Modifier.padding(it))
    }
}