package com.example.openchatting.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openchatting.ui.theme.PrimaryBlue
import com.example.openchatting.ui.theme.SecondaryTeal
import com.example.openchatting.viewModel.SignUp

private val signUpViewModel = SignUp()

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onSignUpSuccess: () -> Unit = {},
    onBackToLogin: () -> Unit = {}
) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onBackground
    val secondaryTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    signUpViewModel.errorMessage?.let { error ->
        LaunchedEffect(error) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            signUpViewModel.clearError()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Header
            Text(
                text = "Create Account",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor
            )

            Text(
                text = "Join us to start chatting",
                fontSize = 16.sp,
                color = secondaryTextColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Username
            ModernTextField(
                value = signUpViewModel.username,
                onValueChange = { signUpViewModel.username = it },
                placeholder = "Username",
                icon = Icons.Default.Person
            )

            // Email
            ModernTextField(
                value = signUpViewModel.email,
                onValueChange = { signUpViewModel.email = it },
                placeholder = "Email Address",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                icon = Icons.Default.Email
            )

            // Phone
            ModernTextField(
                value = signUpViewModel.phoneNumber,
                onValueChange = { signUpViewModel.phoneNumber = it },
                placeholder = "Phone Number",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                icon = Icons.Default.Phone
            )

            // Password
            ModernTextField(
                value = signUpViewModel.password,
                onValueChange = { signUpViewModel.password = it },
                placeholder = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (signUpViewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                icon = Icons.Default.Lock,
                trailingIcon = {
                    IconButton(onClick = { signUpViewModel.isPasswordVisible = !signUpViewModel.isPasswordVisible }) {
                        Text(if (signUpViewModel.isPasswordVisible) "👁️" else "👁️‍🗨️", fontSize = 18.sp)
                    }
                }
            )

            // Confirm Password
            ModernTextField(
                value = signUpViewModel.confirmPassword,
                onValueChange = { signUpViewModel.confirmPassword = it },
                placeholder = "Confirm Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (signUpViewModel.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                icon = Icons.Default.Lock,
                trailingIcon = {
                    IconButton(onClick = { signUpViewModel.isConfirmPasswordVisible = !signUpViewModel.isConfirmPasswordVisible }) {
                        Text(if (signUpViewModel.isConfirmPasswordVisible) "👁️" else "👁️‍🗨️", fontSize = 18.sp)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sign Up Button
            Button(
                onClick = {
                    signUpViewModel.signUp { email ->
                        Toast.makeText(context, "Welcome $email!", Toast.LENGTH_SHORT).show()
                        onSignUpSuccess()
                    }
                },
                enabled = !signUpViewModel.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Gray
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(PrimaryBlue, SecondaryTeal)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (signUpViewModel.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            "Sign Up",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // Login Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Already have an account? ",
                    fontSize = 14.sp,
                    color = secondaryTextColor
                )
                Text(
                    "Log In",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource(),
                        onClick = onBackToLogin
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}
