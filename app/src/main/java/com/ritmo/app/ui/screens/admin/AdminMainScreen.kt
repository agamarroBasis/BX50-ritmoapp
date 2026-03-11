package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ritmo.app.ui.theme.ElectricCoral
import kotlinx.coroutines.launch

object AdminScreenRoute {
    const val Dashboard = "admin_dashboard"
    const val Finance = "admin_finance"
    const val Students = "admin_students"
    const val Instructors = "admin_instructors"
    const val Classes = "admin_classes"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val menuItems = listOf(
        Pair("Dashboard", AdminScreenRoute.Dashboard),
        Pair("Contabilidad", AdminScreenRoute.Finance),
        Pair("Alumnos", AdminScreenRoute.Students),
        Pair("Instructores", AdminScreenRoute.Instructors),
        Pair("Clases", AdminScreenRoute.Classes)
    )

    var currentRoute by remember { mutableStateOf(AdminScreenRoute.Dashboard) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                menuItems.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.first) },
                        selected = item.second == currentRoute,
                        onClick = {
                            scope.launch { drawerState.close() }
                            currentRoute = item.second
                            navController.navigate(item.second) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = ElectricCoral.copy(alpha = 0.2f),
                            selectedTextColor = ElectricCoral
                        ),
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Panel Administrativo") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Text("☰", style = MaterialTheme.typography.titleLarge)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        ) { innerPadding ->
            AdminNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun AdminNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AdminScreenRoute.Dashboard,
        modifier = modifier
    ) {
        composable(AdminScreenRoute.Dashboard) { AdminDashboardScreen() }
        composable(AdminScreenRoute.Finance) { AdminFinanceScreen() }
        composable(AdminScreenRoute.Students) { AdminStudentsScreen() }
        composable(AdminScreenRoute.Instructors) { AdminInstructorsScreen() }
        composable(AdminScreenRoute.Classes) { AdminClassesScreen() }
    }
}
