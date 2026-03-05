package com.example.openchatting.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openchatting.data.Account
import com.example.openchatting.data.AccountList
import com.example.openchatting.ui.theme.PrimaryBlue
import com.example.openchatting.ui.theme.SecondaryTeal

@Composable
fun ChatListScreenContent(onLogout: () -> Unit = {}) {
    var selectedScreen by remember { mutableIntStateOf(0) } // 0: Chat, 1: Settings, 2: Profile

    when (selectedScreen) {
        0 -> ChatList(
            accountList = AccountList.accountList,
            onSettingsClick = { selectedScreen = 1 },
            onProfileClick = { selectedScreen = 2 }
        )
        1 -> SettingsScreen(
            onNavigateToChat = { selectedScreen = 0 },
            onNavigateToSettings = { selectedScreen = 1 },
            onNavigateToProfile = { selectedScreen = 2 }
        )
        2 -> ProfileScreen(
            onNavigateToChat = { selectedScreen = 0 },
            onNavigateToSettings = { selectedScreen = 1 },
            onNavigateToProfile = { selectedScreen = 2 },
            onLogout = onLogout
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatList(
    accountList: List<Account>,
    onSettingsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val appBarColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    val appBarTextColor = MaterialTheme.colorScheme.onPrimary

    var isAppBarHidden by remember { mutableStateOf(false) }
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedNavItem by remember { mutableIntStateOf(0) }

    // Filter accounts based on search query
    val filteredAccountList = if (searchQuery.isEmpty()) {
        accountList
    } else {
        accountList.filter { account ->
            account.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            AnimatedVisibility(
                visible = !isAppBarHidden,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TopAppBar(
                    title = if (isSearchVisible) {
                        {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                placeholder = { Text("Search by name...", fontSize = 14.sp) },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                )
                            )
                        }
                    } else {
                        {
                            Text(
                                "Messages",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = appBarTextColor
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = appBarColor,
                        titleContentColor = appBarTextColor
                    ),
                    actions = {
                        IconButton(
                            onClick = {
                                isSearchVisible = !isSearchVisible
                                if (!isSearchVisible) searchQuery = ""
                            }
                        ) {
                            Text("🔍", fontSize = 20.sp)
                        }
                    }
                )
            }
        },
        bottomBar = {
            CustomAnimatedNavigationBar(
                selectedItem = selectedNavItem,
                onChatClick = { 
                    selectedNavItem = 0 
                },
                onSettingsClick = {
                    selectedNavItem = 1
                    onSettingsClick()
                },
                onProfileClick = {
                    selectedNavItem = 2
                    onProfileClick()
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredAccountList) { account ->
                ChatMessageCard(
                    accountDetails = account
                )
            }
        }
    }
}

@Composable
fun ChatMessageCard(
    accountDetails: Account
) {
    val cardColor = MaterialTheme.colorScheme.surface
    val primaryTextColor = MaterialTheme.colorScheme.onSurface
    val secondaryTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Profile Picture
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(PrimaryBlue, SecondaryTeal)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = accountDetails.profilePicture),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // Message Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = accountDetails.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryTextColor,
                    maxLines = 1
                )
                Text(
                    text = accountDetails.lastMessage,
                    fontSize = 13.sp,
                    color = secondaryTextColor,
                    maxLines = 1
                )
            }

            // Time
            Text(
                text = accountDetails.lastMessageTime,
                fontSize = 12.sp,
                color = secondaryTextColor
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp",
)
@Composable
fun ChatListScreenPreview() {
    ChatListScreenContent()
}
