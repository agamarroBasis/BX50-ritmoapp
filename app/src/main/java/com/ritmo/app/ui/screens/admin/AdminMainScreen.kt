package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.*

private enum class AdminTab { DASHBOARD, STUDENTS, INSTRUCTORS, CLASSES, FINANCE }

@Composable
fun AdminMainScreen(onLogout: () -> Unit = {}) {
    var currentTab by remember { mutableStateOf(AdminTab.DASHBOARD) }

    Scaffold(
        containerColor = LightBackground,
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceLight,
                tonalElevation = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                val items = listOf(
                    Triple(AdminTab.DASHBOARD,   "Dashboard",   "📊"),
                    Triple(AdminTab.STUDENTS,    "Alumnos",     "👥"),
                    Triple(AdminTab.INSTRUCTORS, "Instructores","🎓"),
                    Triple(AdminTab.CLASSES,     "Clases",      "📅"),
                    Triple(AdminTab.FINANCE,     "Finanzas",    "💰")
                )
                items.forEach { (tab, label, icon) ->
                    NavigationBarItem(
                        selected = currentTab == tab,
                        onClick = { currentTab = tab },
                        icon = { Text(icon, fontSize = 18.sp) },
                        label = {
                            Text(
                                label,
                                fontSize = 10.sp,
                                fontWeight = if (currentTab == tab) FontWeight.Bold else FontWeight.Medium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = IndigoAccent,
                            unselectedTextColor = TextSecondary,
                            indicatorColor = IndigoBg
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentTab) {
                AdminTab.DASHBOARD   -> AdminDashboardScreen()
                AdminTab.STUDENTS    -> AdminStudentsScreen()
                AdminTab.INSTRUCTORS -> AdminInstructorsScreen()
                AdminTab.CLASSES     -> AdminClassesScreen()
                AdminTab.FINANCE     -> AdminFinanceScreen()
            }
        }
    }
}
