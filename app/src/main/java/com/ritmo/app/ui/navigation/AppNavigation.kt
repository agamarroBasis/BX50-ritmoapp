package com.ritmo.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ritmo.app.ui.screens.login.LoginScreen
import com.ritmo.app.ui.screens.admin.AdminMainScreen

object Screen {
    const val Login = "login"
    const val AdminMain = "admin_main"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login) {
        composable(Screen.Login) {
            LoginScreen(
                onLoginSuccess = { role ->
                    if (role == "Admin") {
                        navController.navigate(Screen.AdminMain) {
                            popUpTo(Screen.Login) { inclusive = true }
                        }
                    } else {
                        // For instructor, normally would navigate to InstructorMain
                        // Based on requirement we focus on Admin access
                    }
                }
            )
        }

        composable(Screen.AdminMain) {
            AdminMainScreen()
        }
    }
}
