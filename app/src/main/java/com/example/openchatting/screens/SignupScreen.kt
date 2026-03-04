package com.example.openchatting.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import com.example.openchatting.R
import com.example.openchatting.viewModel.SignUp

val signUpViewModel = SignUp()

@Composable
fun SignupScreenContent(
    modifier: Modifier = Modifier,
    onSignUpSuccess: () -> Unit = {},
    onBackToLogin: () -> Unit = {}
) {
    val context = LocalContext.current
    val isDarkMode = isSystemInDarkTheme()
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.LightGray else Color.Gray
    val buttonTextColor = if (isDarkMode) Color.White else Color.Black
    val buttonBorderColor = if (isDarkMode) Color.White else Color.Black
    val buttonBackgroundColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 20.dp, bottom = 20.dp),
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

            Text(
                text = "Create Your Account",
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Username Field
            SignupInputField(
                value = signUpViewModel.username,
                onValueChange = { signUpViewModel.username = it },
                placeholder = "Username",
                modifier = Modifier.padding(10.dp),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = textColor,
                    )
                }
            )

            // Email Field
            SignupInputField(
                value = signUpViewModel.email,
                onValueChange = { signUpViewModel.email = it },
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

            // Phone Number Field
            SignupInputField(
                value = signUpViewModel.phoneNumber,
                onValueChange = { signUpViewModel.phoneNumber = it },
                placeholder = "Phone Number",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = textColor,
                    )
                }
            )

            // Password Field
            SignupInputField(
                value = signUpViewModel.password,
                onValueChange = { signUpViewModel.password = it },
                placeholder = "Password",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (signUpViewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = textColor,
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { signUpViewModel.isPasswordVisible = !signUpViewModel.isPasswordVisible }) {
                        Icon(
                            painter = painterResource(if (signUpViewModel.isPasswordVisible) R.drawable.hidden else R.drawable.eye),
                            contentDescription = if (signUpViewModel.isPasswordVisible) "Hide password" else "Show password",
                            tint = textColor,
                            modifier = Modifier.size(26.dp)
                                .offset(x = (-5).dp)
                        )
                    }
                }
            )

            // Confirm Password Field
            SignupInputField(
                value = signUpViewModel.confirmPassword,
                onValueChange = { signUpViewModel.confirmPassword = it },
                placeholder = "Confirm Password",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (signUpViewModel.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = textColor,
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { signUpViewModel.isConfirmPasswordVisible = !signUpViewModel.isConfirmPasswordVisible }) {
                        Icon(
                            painter = painterResource(if (signUpViewModel.isConfirmPasswordVisible) R.drawable.hidden else R.drawable.eye),
                            contentDescription = if (signUpViewModel.isConfirmPasswordVisible) "Hide password" else "Show password",
                            tint = textColor,
                            modifier = Modifier.size(26.dp)
                                .offset(x = (-5).dp)
                        )
                    }
                }
            )

            // Sign Up Button
            Button(
                onClick = {
                    when {
                        signUpViewModel.username.isEmpty() || signUpViewModel.email.isEmpty() ||
                        signUpViewModel.password.isEmpty() || signUpViewModel.confirmPassword.isEmpty() ||
                        signUpViewModel.phoneNumber.isEmpty() -> {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }
                        signUpViewModel.password != signUpViewModel.confirmPassword -> {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        }
                        signUpViewModel.password.length < 6 -> {
                            Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                        }
                        !signUpViewModel.email.contains("@") -> {
                            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
                        }
                        signUpViewModel.phoneNumber.length < 10 -> {
                            Toast.makeText(context, "Phone number must be at least 10 digits", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                            // Clear form
                            signUpViewModel.username = ""
                            signUpViewModel.email = ""
                            signUpViewModel.password = ""
                            signUpViewModel.confirmPassword = ""
                            signUpViewModel.phoneNumber = ""
                            onSignUpSuccess()
                        }
                    }
                },
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
                Text(
                    "Sign Up",
                    color = buttonTextColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily.Cursive
                )
            }
            Row(
                modifier = Modifier.padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    color = secondaryTextColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp),
                    fontFamily = FontFamily.Cursive
                )
                Text(
                    text = "Log In",
                    color = textColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable(onClick = onBackToLogin),
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    }
}

@Composable
fun SignupInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    icon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val isDarkMode = isSystemInDarkTheme()
    val textColor = if (isDarkMode) Color.White else Color.Black
    val placeholderColor = if (isDarkMode) Color.LightGray else Color.Gray
    val borderColor = if (isDarkMode) Color.White else Color.Black
    val cursorColor = if (isDarkMode) Color.White else Color.Black

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = FontFamily.Cursive,
                color = placeholderColor
            )
        },
        leadingIcon = icon,
        trailingIcon = trailingIcon,
        shape = CircleShape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            cursorColor = cursorColor,
            focusedLabelColor = borderColor,
        ),
        maxLines = 1,
        singleLine = true,
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignupScreenPreview() {
    SignupScreenContent()
}

