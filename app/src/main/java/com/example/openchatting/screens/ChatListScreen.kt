package com.example.openchatting.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openchatting.data.Account
import com.example.openchatting.data.AccountList


@Composable
fun ChatListScreenContent() {
    ChatList(
        accountList = AccountList.accountList,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatList(
    accountList: List<Account>
) {
    val isDarkMode = isSystemInDarkTheme()
    val appBarColor = if (isDarkMode) Color.DarkGray else Color.LightGray
    val appBarTextColor = if (isDarkMode) Color.White else Color.Black
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFFAFAFA)

    var isAppBarHidden by remember { mutableStateOf(false) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                if (delta < -5) {
                    isAppBarHidden = true
                }

                else if (delta > 5) {
                    isAppBarHidden = false
                }

                return Offset.Zero
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection),
        containerColor = backgroundColor,
        topBar = {

            AnimatedVisibility(
                visible = !isAppBarHidden,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TopAppBar(
                    title = { Text(
                        "Chatty",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Cursive,
                        color = appBarTextColor
                    ) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = appBarColor,
                        titleContentColor = appBarTextColor
                    ),
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(accountList) { account ->
                ChatMessageCard(
                    accountDetails = Account(
                        account.name,
                        account.profilePicture,
                        account.lastMessage,
                        account.lastMessageTime
                    )
                )
            }
        }
    }
}

@Composable
fun ChatMessageCard(
    accountDetails: Account
) {
    val isDarkMode = isSystemInDarkTheme()
    val primaryTextColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.LightGray else Color.Gray

    val painter = painterResource(id = accountDetails.profilePicture)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
            ){
                Image(
                    painter = painter,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier,
                    text = accountDetails.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Start,
                    color = primaryTextColor
                )
                Text(
                    modifier = Modifier,
                    text = accountDetails.lastMessage,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Cursive,
                    color = secondaryTextColor
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = accountDetails.lastMessageTime,
                textAlign = TextAlign.End,
                fontSize = 18.sp,
                fontFamily = FontFamily.Cursive,
                color = secondaryTextColor
            )
        }
    }
}

@Suppress("UNUSED_PARAMETER")
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp",
)
@Composable
fun ChatListScreenPreview() {
    ChatListScreenContent()
}