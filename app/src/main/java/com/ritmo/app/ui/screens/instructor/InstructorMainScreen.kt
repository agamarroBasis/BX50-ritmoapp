package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.*

private enum class InstructorTab { HOME, SCHEDULE, STUDENTS, PROFILE }

private val STUDENTS_LIST = listOf(
    Triple("Maria Garcia",    "MG", Color(0xFF6366F1)),
    Triple("Juan Pérez",      "JP", Color(0xFF50CD89)),
    Triple("Sofia Rodriguez", "SR", Color(0xFFFF5236)),
    Triple("Carlos López",    "CL", Color(0xFFF5A623)),
    Triple("Ana Martínez",    "AM", Color(0xFF009EF7)),
    Triple("Luis Fernández",  "LF", Color(0xFFEC4899)),
    Triple("Valentina Cruz",  "VC", Color(0xFF14B8A6)),
    Triple("Diego Herrera",   "DH", Color(0xFF6366F1))
)

@Composable
fun InstructorMainScreen(onLogout: () -> Unit) {
    var currentTab by remember { mutableStateOf(InstructorTab.HOME) }
    var showAttendance by remember { mutableStateOf(false) }
    var selectedStudentName by remember { mutableStateOf<String?>(null) }

    // Full-screen overlays (not tied to bottom nav)
    if (showAttendance) {
        AttendanceScreen(onBackClicked = { showAttendance = false })
        return
    }
    if (selectedStudentName != null) {
        StudentProfile(studentName = selectedStudentName!!, onBack = { selectedStudentName = null })
        return
    }

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
                    Triple(InstructorTab.HOME,     "Inicio",   "🏠"),
                    Triple(InstructorTab.SCHEDULE, "Horario",  "📅"),
                    Triple(InstructorTab.STUDENTS, "Alumnos",  "👥"),
                    Triple(InstructorTab.PROFILE,  "Perfil",   "👤")
                )
                items.forEach { (tab, label, icon) ->
                    NavigationBarItem(
                        selected = currentTab == tab,
                        onClick = { currentTab = tab },
                        icon = { Text(icon, fontSize = 20.sp) },
                        label = {
                            Text(
                                label,
                                fontSize = 11.sp,
                                fontWeight = if (currentTab == tab) FontWeight.Bold else FontWeight.Medium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = ElectricCoral,
                            unselectedTextColor = TextSecondary,
                            indicatorColor = CoralBg
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentTab) {
                InstructorTab.HOME     -> InstructorDashboard(onNavigateToAttendance = { showAttendance = true })
                InstructorTab.SCHEDULE -> ScheduleScreen()
                InstructorTab.STUDENTS -> StudentsListScreen(onStudentSelected = { selectedStudentName = it })
                InstructorTab.PROFILE  -> InstructorProfileScreen(onLogout = onLogout)
            }
        }
    }
}

@Composable
private fun StudentsListScreen(onStudentSelected: (String) -> Unit) {
    var search by remember { mutableStateOf("") }
    val filtered = STUDENTS_LIST.filter { it.first.contains(search, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceLight)
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Text("Mis Alumnos", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
            Text("${STUDENTS_LIST.size} alumnos en tus clases", fontSize = 13.sp, color = TextSecondary)
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Buscar alumno...", color = TextTertiary) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ElectricCoral,
                    unfocusedBorderColor = BorderColor
                )
            )
        }

        Spacer(Modifier.height(2.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filtered) { (name, initials, color) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onStudentSelected(name) },
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(color),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(initials, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Column(Modifier.weight(1f)) {
                            Text(name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                            Text("Intermedio · Activo", fontSize = 12.sp, color = TextSecondary)
                        }
                        Text("›", fontSize = 20.sp, color = TextTertiary, fontWeight = FontWeight.Light)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InstructorProfileScreen(onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceLight)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(CoralBg),
                contentAlignment = Alignment.Center
            ) {
                Text("AG", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = ElectricCoral)
            }
            Spacer(Modifier.height(12.dp))
            Text("Andrés García", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
            Text("Instructor Certificado", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(CoralBg)
                    .padding(horizontal = 12.dp, vertical = 5.dp)
            ) {
                Text("Salsa · Bachata · Merengue", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = ElectricCoral)
            }
        }

        Spacer(Modifier.height(12.dp))

        // Stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceLight)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileStatSmall("4", "Clases/sem", ElectricCoral)
            Box(Modifier.width(1.dp).height(36.dp).background(BorderColor))
            ProfileStatSmall("3", "Años exp.", IndigoAccent)
            Box(Modifier.width(1.dp).height(36.dp).background(BorderColor))
            ProfileStatSmall("45+", "Alumnos", SuccessGreen)
        }

        Spacer(Modifier.height(12.dp))

        // Menu items
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceLight),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column {
                listOf(
                    "✏️" to "Editar perfil",
                    "🔔" to "Notificaciones",
                    "🔐" to "Cambiar contraseña",
                    "❓" to "Ayuda y soporte"
                ).forEachIndexed { i, (icon, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(icon, fontSize = 18.sp)
                        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextPrimary, modifier = Modifier.weight(1f))
                        Text("›", fontSize = 18.sp, color = TextTertiary)
                    }
                    if (i < 3) HorizontalDivider(color = BorderColor, modifier = Modifier.padding(start = 46.dp))
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Logout
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DangerBg,
                contentColor = DangerRed
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text("Cerrar sesión", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ProfileStatSmall(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = color)
        Text(label, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
    }
}
