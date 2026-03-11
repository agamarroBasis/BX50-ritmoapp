package com.ritmo.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ritmo.app.ui.screens.instructor.AttendanceScreen
import com.ritmo.app.ui.screens.instructor.InstructorDashboard
import com.ritmo.app.ui.screens.instructor.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("instructor_dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("instructor_dashboard") {
            InstructorDashboard(
                onNavigateToAttendance = {
                    navController.navigate("attendance")
                }
            )
        }
        composable("attendance") {
            AttendanceScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
