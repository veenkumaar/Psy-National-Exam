package com.example.psynationalexam.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.psynationalexam.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomSplashScreenUI()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

@Preview(showSystemUi = true)
@Composable
fun CustomSplashScreenUI(){
    // Simple splash UI, you can customize as needed
    androidx.compose.material3.Surface {
        androidx.compose.material3.Text(
            text = "Welcome to PsyNationalExam!",
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .wrapContentSize(),
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
    }
}