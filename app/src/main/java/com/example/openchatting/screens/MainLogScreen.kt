package com.example.openchatting.screens

import android.R.attr.textColor
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext
import com.example.openchatting.R
import com.example.openchatting.viewModel.LogIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect

@Composable
fun MainInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    icon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = FontFamily.Cursive
            )
        },
        leadingIcon = icon,
        trailingIcon = trailingIcon,
        shape = CircleShape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
        ),
        maxLines = 1,
        singleLine = true,
    )
}
val viewModel = LogIn()
@Composable
fun MainLogScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val isDarkMode = isSystemInDarkTheme()
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.LightGray else Color.Gray
    val buttonTextColor = if (isDarkMode) Color.White else Color.Black
    val buttonBorderColor = if (isDarkMode) Color.White else Color.Black
    val buttonBackgroundColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White

    // Show error message if any
    viewModel.errorMessage?.let { error ->
        LaunchedEffect(error) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .offset(y = (-20).dp)
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.chat_icon),
                    contentDescription = "Chat Icon",
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "OpenChatting",
                    color = textColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(start = 8.dp),
                    fontFamily = FontFamily.Cursive
                )
            }
            MainInputField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                placeholder = "Email",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = textColor,
                    )
                }
            )
            MainInputField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                placeholder = "Password",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = textColor,
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.isPasswordVisible = !viewModel.isPasswordVisible }) {
                        Icon(
                            painter = painterResource(if (viewModel.isPasswordVisible) R.drawable.hidden else R.drawable.eye),
                            contentDescription = if (viewModel.isPasswordVisible) "Hide password" else "Show password",
                            tint = textColor,
                            modifier = Modifier.size(26.dp)
                                .offset(x = (-5).dp)
                        )
                    }
                }
            )
            Button(
                onClick = {
                    viewModel.signIn { user ->
                        Toast.makeText(context, "Welcome ${user.email}!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }
                },
                enabled = !viewModel.isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                ),
                border = BorderStroke(2.dp, buttonBorderColor),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = false
                    ),
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = buttonTextColor
                    )
                } else {
                    Text(
                        "Sign In",
                        color = buttonTextColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily.Cursive
                    )
                }
            }
            Row {
                Text(
                    text = "You don't have an account?",
                    color = secondaryTextColor,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily.Cursive
                )
                Text(
                    text = "Sign Up",
                    color = textColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = onSignUpClick),
                    fontFamily = FontFamily.Cursive
                )
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                ),
                border = BorderStroke(2.dp, buttonBorderColor),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = false
                    ),
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    "Continue with Google",
                    textAlign = TextAlign.End,
                    color = buttonTextColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainLogPreview() {
    Scaffold { padding ->
        MainLogScreen(modifier = Modifier.padding(padding))
    }
}
