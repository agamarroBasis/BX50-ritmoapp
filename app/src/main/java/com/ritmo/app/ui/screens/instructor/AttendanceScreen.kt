package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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

data class Student(val id: Int, val name: String, val initials: String, val avatarColor: Color, var isPresent: Boolean? = null)

private val AVATAR_COLORS = listOf(
    Color(0xFF6366F1), Color(0xFF50CD89), Color(0xFFFF5236), Color(0xFFF5A623),
    Color(0xFF009EF7), Color(0xFFEC4899), Color(0xFF14B8A6)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(onBackClicked: () -> Unit) {
    val students = remember {
        mutableStateListOf(
            Student(1, "Maria Garcia",    "MG", AVATAR_COLORS[0]),
            Student(2, "Juan Pérez",      "JP", AVATAR_COLORS[1]),
            Student(3, "Sofia Rodriguez", "SR", AVATAR_COLORS[2]),
            Student(4, "Carlos López",    "CL", AVATAR_COLORS[3]),
            Student(5, "Ana Martínez",    "AM", AVATAR_COLORS[4]),
            Student(6, "Luis Fernández",  "LF", AVATAR_COLORS[5]),
            Student(7, "Valentina Cruz",  "VC", AVATAR_COLORS[6]),
            Student(8, "Diego Herrera",   "DH", AVATAR_COLORS[0])
        )
    }

    val markedCount = students.count { it.isPresent != null }
    val presentCount = students.count { it.isPresent == true }
    val progress = if (students.isNotEmpty()) markedCount.toFloat() / students.size else 0f

    Scaffold(
        containerColor = LightBackground,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Salsa On2", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                        Text("Intermedio • 14:00 – 15:30", fontSize = 12.sp, color = TextSecondary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceLight),
                windowInsets = WindowInsets(0.dp)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Button(
                    onClick = onBackClicked,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricCoral),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text("Guardar Asistencia ($presentCount presentes)", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            // ── Progress summary ──
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Progreso de asistencia", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                                Text("$markedCount de ${students.size} alumnos marcados", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            }
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(CoralBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("${(progress * 100).toInt()}%", fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, color = ElectricCoral)
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                            color = ElectricCoral,
                            trackColor = BorderColor
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            LegendDot(color = SuccessGreen, label = "$presentCount Presentes")
                            LegendDot(color = DangerRed, label = "${students.count { it.isPresent == false }} Ausentes")
                            LegendDot(color = BorderColor, label = "${students.size - markedCount} Pendientes")
                        }
                    }
                }
            }

            // ── Student rows ──
            itemsIndexed(students) { index, student ->
                AttendanceRow(
                    student = student,
                    onMarkPresent = {
                        students[index] = students[index].copy(isPresent = true)
                    },
                    onMarkAbsent = {
                        students[index] = students[index].copy(isPresent = false)
                    }
                )
            }
        }
    }
}

@Composable
private fun LegendDot(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
        Text(label, fontSize = 12.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun AttendanceRow(student: Student, onMarkPresent: () -> Unit, onMarkAbsent: () -> Unit) {
    val bgColor = when (student.isPresent) {
        true  -> SuccessBg
        false -> DangerBg
        null  -> SurfaceLight
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(student.avatarColor),
                contentAlignment = Alignment.Center
            ) {
                Text(student.initials, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            // Name
            Text(student.name, modifier = Modifier.weight(1f), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)

            // Status indicator
            when (student.isPresent) {
                true  -> Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(SuccessGreen))
                false -> Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(DangerRed))
                null  -> Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(BorderColor))
            }

            // Present button
            IconButton(
                onClick = onMarkPresent,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (student.isPresent == true) SuccessGreen else BorderColor.copy(alpha = 0.5f))
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Presente",
                    tint = if (student.isPresent == true) Color.White else TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Absent button
            IconButton(
                onClick = onMarkAbsent,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (student.isPresent == false) DangerRed else BorderColor.copy(alpha = 0.5f))
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Ausente",
                    tint = if (student.isPresent == false) Color.White else TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
