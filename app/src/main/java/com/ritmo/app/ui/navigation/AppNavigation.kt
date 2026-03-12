package com.ritmo.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ritmo.app.ui.screens.admin.AdminMainScreen
import com.ritmo.app.ui.screens.instructor.InstructorMainScreen
import com.ritmo.app.ui.screens.login.LoginScreen
import com.ritmo.app.ui.screens.walkthrough.WalkthroughScreen

object Screen {
    const val Walkthrough    = "walkthrough"
    const val Login          = "login"
    const val InstructorMain = "instructor_main"
    const val AdminMain      = "admin_main"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Walkthrough) {

        composable(Screen.Walkthrough) {
            WalkthroughScreen(
                onFinished = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Walkthrough) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login) {
            LoginScreen(
                onLoginSuccess = { role ->
                    val dest = if (role == "Admin") Screen.AdminMain else Screen.InstructorMain
                    navController.navigate(dest) {
                        popUpTo(Screen.Login) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.InstructorMain) {
            InstructorMainScreen(
                onLogout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.InstructorMain) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.AdminMain) {
            AdminMainScreen(
                onLogout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.AdminMain) { inclusive = true }
                    }
                }
            )
        }
    }
}
