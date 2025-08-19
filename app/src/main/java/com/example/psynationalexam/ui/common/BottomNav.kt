package com.example.psynationalexam.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController

sealed class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Library : BottomNavItem("library", "Library", Icons.Filled.MenuBook) // label hidden when not selected
    object Profile : BottomNavItem("profile", "Profile", Icons.Filled.AccountCircle)
    companion object {
        val items = listOf(Home, Library, Profile)
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination
    NavigationBar(containerColor = Color.White) {
        BottomNavItem.items.forEach { item ->
            val selected = destination.isRouteSelected(item.route)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label.ifBlank { item.route }
                    )
                },
                label = if (item.label.isNotBlank()) { { Text(item.label) } } else null,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF3B5FFF),
                    selectedTextColor = Color(0xFF3B5FFF),
                    unselectedIconColor = Color(0xFF9A9A9A),
                    unselectedTextColor = Color(0xFF9A9A9A),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

private fun NavDestination?.isRouteSelected(route: String): Boolean =
    this?.hierarchy?.any { it.route == route } == true