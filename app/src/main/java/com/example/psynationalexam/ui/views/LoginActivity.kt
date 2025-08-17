package com.example.psynationalexam.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.psynationalexam.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psynationalexam.MainActivity

class LoginActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			LoginScreen(
				onLoginClicked = {
					startActivity(Intent(this, MainActivity::class.java))
					finish()
				},
				onSignUpClicked = {
					startActivity(Intent(this, SignupActivity::class.java))
					finish()
				},
				onForgotPasswordClicked = {
					startActivity(Intent(this, MainActivity::class.java))
					finish()
				}
			)
		}
	}
}

@Composable
fun LoginScreen(onLoginClicked: () -> Unit, onSignUpClicked: () -> Unit, onForgotPasswordClicked: () -> Unit) {
	var email by remember { mutableStateOf("Cooper_Kristin@gmail.com") }
	var password by remember { mutableStateOf("password") }
	var passwordVisible by remember { mutableStateOf(false) }

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.align(Alignment.TopCenter),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(Color(0xFFF4F4F4), shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
			) {
				Text(
					text = "Log In",
					fontWeight = FontWeight.Bold,
					fontSize = 32.sp,
					color = Color(0xFF23223C),
					modifier = Modifier
						.padding(start = 24.dp, top = 40.dp, bottom = 32.dp)
				)
			}
			Spacer(modifier = Modifier.height(32.dp))
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 24.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(
					text = "Your  Email",
					color = Color(0xFF9A9A9A),
					fontSize = 14.sp,
					modifier = Modifier.align(Alignment.Start)
				)
				Spacer(modifier = Modifier.height(8.dp))
				OutlinedTextField(
					value = email,
					onValueChange = { email = it },
					singleLine = true,
					shape = RoundedCornerShape(12.dp),
					modifier = Modifier
						.fillMaxWidth(),
					textStyle = TextStyle(fontSize = 16.sp),
					colors = OutlinedTextFieldDefaults.colors(
						focusedBorderColor = Color(0xFF3B5FFF),
						unfocusedBorderColor = Color(0xFFE5E5F0)
					)
				)
				Spacer(modifier = Modifier.height(20.dp))
				Text(
					text = "Password",
					color = Color(0xFF9A9A9A),
					fontSize = 14.sp,
					modifier = Modifier.align(Alignment.Start)
				)
				Spacer(modifier = Modifier.height(8.dp))
				OutlinedTextField(
					value = password,
					onValueChange = { password = it },
					singleLine = true,
					shape = RoundedCornerShape(12.dp),
					modifier = Modifier
						.fillMaxWidth(),
					textStyle = TextStyle(fontSize = 16.sp),
					visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
					trailingIcon = {
						val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
						IconButton(onClick = { passwordVisible = !passwordVisible }) {
							Icon(imageVector = image, contentDescription = null, tint = Color(0xFF9A9A9A))
						}
					},
					colors = OutlinedTextFieldDefaults.colors(
						focusedBorderColor = Color(0xFF3B5FFF),
						unfocusedBorderColor = Color(0xFFE5E5F0)
					)
				)
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.End
				) {
					Text(
						text = "Forget password?",
						color = Color(0xFF9A9A9A),
						fontSize = 14.sp,
						modifier = Modifier
							.padding(top = 8.dp)
							.clickable { onForgotPasswordClicked() }
					)
				}
				Spacer(modifier = Modifier.height(20.dp))
				Button(
					onClick = { onLoginClicked() },
					modifier = Modifier
						.fillMaxWidth()
						.height(56.dp),
					shape = RoundedCornerShape(16.dp),
					colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5FFF))
				) {
					Text("Log In", color = Color.White, fontSize = 18.sp)
				}
				Spacer(modifier = Modifier.height(16.dp))
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center
				) {
					Text(
						text = "Don't have an account? ",
						color = Color(0xFF9A9A9A),
						fontSize = 14.sp
					)
					Text(
						text = "Sign up",
						color = Color(0xFF3B5FFF),
						fontSize = 14.sp,
						fontWeight = FontWeight.Bold,
						modifier = Modifier.clickable { onSignUpClicked() }
					)
				}
				Spacer(modifier = Modifier.height(24.dp))
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier.fillMaxWidth()
				) {
					Divider(modifier = Modifier.weight(1f), color = Color(0xFFE5E5F0))
					Text(
						text = "  Or login with  ",
						color = Color(0xFF9A9A9A),
						fontSize = 14.sp
					)
					Divider(modifier = Modifier.weight(1f), color = Color(0xFFE5E5F0))
				}
				Spacer(modifier = Modifier.height(24.dp))
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center
				) {
					// Replace with your own icons if needed
					IconButton(
						onClick = { /* TODO: Google login */ },
						modifier = Modifier
							.size(48.dp)
							.background(Color.White, shape = CircleShape)
					) {
						Image(
							painter = painterResource(id = R.drawable.google),
							contentDescription = "Google",
							modifier = Modifier.size(32.dp)
						)
					}
					Spacer(modifier = Modifier.width(24.dp))
					IconButton(
						onClick = { /* TODO: Facebook login */ },
						modifier = Modifier
							.size(48.dp)
							.background(Color.White, shape = CircleShape)
					) {
						Image(
							painter = painterResource(id = R.drawable.fb),
							contentDescription = "Facebook",
							modifier = Modifier.size(32.dp)
						)
					}
				}
			}
		}
	}
}