package com.example.psynationalexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.psynationalexam.ui.common.BottomNavBar
import com.example.psynationalexam.ui.common.BottomNavItem
import com.example.psynationalexam.ui.theme.PsyNationalExamTheme
import androidx.compose.material3.Text
import com.example.psynationalexam.ui.views.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PsyNationalExamTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavItem.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(BottomNavItem.Home.route) { HomeScreen() }
                        composable(BottomNavItem.Library.route) { LibraryScreen() }
                        composable(BottomNavItem.Profile.route) { ProfileScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun LibraryScreen() { /* TODO: Implement Library UI */ Text("Library") }

@Composable
fun ProfileScreen() { /* TODO: Implement Profile UI */ Text("Profile") }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    PsyNationalExamTheme {
        val navController = rememberNavController()
        Scaffold(bottomBar = { BottomNavBar(navController) }) { inner ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(inner)
            ) {
                composable(BottomNavItem.Home.route) { HomeScreen() }
                composable(BottomNavItem.Library.route) { LibraryScreen() }
                composable(BottomNavItem.Profile.route) { ProfileScreen() }
            }
        }
    }
}