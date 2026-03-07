package com.example.openchatting.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToChat: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val cardColor = MaterialTheme.colorScheme.surface
    val textColor = MaterialTheme.colorScheme.onBackground
    val secondaryTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    val appBarColor = MaterialTheme.colorScheme.primary
    val appBarTextColor = MaterialTheme.colorScheme.onPrimary
    
    val isSystemDark = isSystemInDarkTheme()

    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    var vibrateEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(isSystemDark) }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Settings",
                        style = MaterialTheme.typography.headlineMedium,
                        color = appBarTextColor
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = appBarColor,
                    titleContentColor = appBarTextColor
                )
            )
        },
        bottomBar = {
            CustomAnimatedNavigationBar(
                selectedItem = 1,
                onChatClick = { onNavigateToChat() },
                onSettingsClick = { onNavigateToSettings() },
                onProfileClick = { onNavigateToProfile() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            SettingItem(
                title = "Notifications",
                description = "Enable push notifications",
                isEnabled = notificationsEnabled,
                onToggle = { notificationsEnabled = it },
                cardColor = cardColor,
                textColor = textColor,
                secondaryTextColor = secondaryTextColor,
                appBarTextColor = appBarTextColor
            )

            SettingItem(
                title = "Sound",
                description = "Enable notification sounds",
                isEnabled = soundEnabled,
                onToggle = { soundEnabled = it },
                cardColor = cardColor,
                textColor = textColor,
                secondaryTextColor = secondaryTextColor,
                appBarTextColor = appBarTextColor
            )

            SettingItem(
                title = "Vibration",
                description = "Vibrate on notifications",
                isEnabled = vibrateEnabled,
                onToggle = { vibrateEnabled = it },
                cardColor = cardColor,
                textColor = textColor,
                secondaryTextColor = secondaryTextColor,
                appBarTextColor = appBarTextColor
            )

            SettingItem(
                title = "Dark Mode",
                description = "Enable dark theme",
                isEnabled = darkModeEnabled,
                onToggle = { darkModeEnabled = it },
                cardColor = cardColor,
                textColor = textColor,
                secondaryTextColor = secondaryTextColor,
                appBarTextColor = appBarTextColor
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        "About",
                        style = MaterialTheme.typography.titleLarge,
                        color = textColor
                    )
                    Text(
                        "App Version: 1.0",
                        style = MaterialTheme.typography.bodySmall,
                        color = secondaryTextColor,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        "© 2024 OpenChatting",
                        style = MaterialTheme.typography.bodySmall,
                        color = secondaryTextColor,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
