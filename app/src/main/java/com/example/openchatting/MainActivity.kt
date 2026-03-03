package com.example.openchatting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() {
                MainLogScreen(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun MainInputField(
    modifier: Modifier = Modifier,
    value: String?,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    icon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value ?: "",
        onValueChange = onValueChange,
        placeholder = { Text(
            text = placeholder,
            fontFamily = FontFamily.Cursive
        ) },
        leadingIcon = icon,
        trailingIcon = trailingIcon,
        shape = CircleShape,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Gray,
            focusedLabelColor = Color.Black,
        ),
        maxLines = 1,
        singleLine = true,
    )
}

@Composable
fun MainLogScreen(
    modifier: Modifier
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.offset(0.dp, (-30).dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = false
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                ) {
                    val image = painterResource(R.drawable.chat_icon)
                    Image(
                        painter = image,
                        contentDescription = "Chat Icon",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = "OpenChatting",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(start = 10.dp),
                    fontFamily = FontFamily.Cursive

                )
            }
            MainInputField(
                value = email,
                onValueChange = {email = it},
                placeholder = "Email",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            )
            MainInputField(
                value = password,
                onValueChange = {password = it},
                placeholder = "Password",
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = if (isPasswordVisible) KeyboardType.Text else KeyboardType.Password),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                icon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                            tint = if (isPasswordVisible) Color.Gray else Color.Black
                        )
                    }
                },
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier.padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Sing Up",
                    color = Color.Black,
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
fun MainLogPreview(){
    Scaffold() {
        MainLogScreen(modifier = Modifier.padding(it))
    }
}