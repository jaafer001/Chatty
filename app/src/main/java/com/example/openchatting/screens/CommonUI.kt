package com.example.openchatting.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openchatting.R
import com.example.openchatting.ui.theme.PrimaryBlue
import com.example.openchatting.ui.theme.SecondaryTeal

@Composable
fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    icon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val focusedBorderColor = PrimaryBlue
    val unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f)
    val textColor = MaterialTheme.colorScheme.onBackground

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, fontSize = 14.sp) },
        leadingIcon = icon?.let { { Icon(it, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(20.dp)) } },
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            cursorColor = PrimaryBlue,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun InfoRow(label: String, value: String, textColor: Color, secondaryTextColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 13.sp, color = secondaryTextColor)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = textColor)
    }
}

@Composable
fun SettingItem(
    title: String,
    description: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    cardColor: Color,
    textColor: Color,
    secondaryTextColor: Color,
    appBarTextColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                Text(
                    description,
                    fontSize = 13.sp,
                    color = secondaryTextColor,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Switch(
                checked = isEnabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = appBarTextColor,
                    checkedTrackColor = PrimaryBlue,
                    uncheckedThumbColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun CustomAnimatedNavigationBar(
    selectedItem: Int,
    onChatClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit,
    appBarColor: Color = MaterialTheme.colorScheme.primary,
    appBarTextColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(0, selectedItem, R.drawable.chat_icon, "Chat", onChatClick)
            NavigationItem(1, selectedItem, R.drawable.settings, "Settings", onSettingsClick)
            NavigationItem(2, selectedItem, R.drawable.profile, "Profile", onProfileClick)
        }
    }
}

@Composable
fun RowScope.NavigationItem(
    index: Int,
    selectedItem: Int,
    iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    val isDarkMode = isSystemInDarkTheme()
    val tintColor = if (selectedItem == index) {
        if (isDarkMode) Color.White else Color.Black
    } else Color.Gray

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .then(
                    if (selectedItem == index)
                        Modifier.drawBehind {
                            drawCircle(
                                color = tintColor,
                                radius = 35.dp.toPx(),
                                center = Offset(x = size.width / 2, y = size.height / 2),
                                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx())
                            )
                        }
                    else Modifier
                )
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(28.dp),
                colorFilter = ColorFilter.tint(tintColor)
            )
            Text(
                label,
                fontSize = 12.sp,
                color = tintColor,
                fontFamily = FontFamily.Cursive,
                fontWeight = if (selectedItem == index) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
