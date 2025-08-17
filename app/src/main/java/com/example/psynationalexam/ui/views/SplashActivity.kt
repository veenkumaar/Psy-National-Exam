package com.example.psynationalexam.ui.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.psynationalexam.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.illustration, // replace with your image resource
        title = "Numerous free\ntrial courses",
        description = "Free courses for you to\nfind your way to learning"
    ),
    OnboardingPage(
        imageRes = R.drawable.illustration2, // replace with your image resource
        title = "Quick and easy\nlearning",
        description = "Easy and fast learning at\nany time to help you\nimprove various skills"
    ),
    OnboardingPage(
        imageRes = R.drawable.illustration3, // replace with your image resource
        title = "Create your own\nstudy plan",
        description = "Study according to the\nstudy plan, make study\nmore motivated"
    )
)

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnboardingScreen(
                onSignupClicked = {
                    startActivity(Intent(this, SignupActivity::class.java))
                    finish()
                },
                onLoginClicked = { // Add a new lambda for login
                    startActivity(Intent(this, LoginActivity::class.java)) // Or your LoginActivity
                    finish() // Optionally finish SplashActivity
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(onSignupClicked: () -> Unit, onLoginClicked: () -> Unit) { // Accept the new lambda
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current // Get context here

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (pagerState.currentPage < onboardingPages.lastIndex) {
                    TextButton(onClick = onSignupClicked) { // This correctly uses the lambda from Activity
                        Text("Skip", color = Color(0xFF9A9A9A))
                    }
                }
            }
            HorizontalPager(
                count = onboardingPages.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPageContent(onboardingPages[page])
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color(0xFF3B5FFF),
                inactiveColor = Color(0xFFE5E5F0),
                modifier = Modifier.padding(16.dp)
            )
            if (pagerState.currentPage == onboardingPages.lastIndex) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            onSignupClicked()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5FFF))
                    ) {
                        Text("Sign up", color = Color.White, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedButton(
                        onClick = {
                            onLoginClicked()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF3B5FFF))
                    ) {
                        Text("Log in", fontSize = 18.sp)
                    }
                }
            } else {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5FFF))
                ) {
                    Text("Next", color = Color.White, fontSize = 18.sp)
                }
                // Spacer(modifier = Modifier.height(56.dp)) // Old spacer
            }
        }
    }
}
@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .background(Color(0xFFE3F2FD), shape = RoundedCornerShape(48.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = page.title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF23223C),
            lineHeight = 32.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color(0xFF9A9A9A),
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
    }
}