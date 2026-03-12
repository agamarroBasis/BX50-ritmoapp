package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.*

private data class TodayClass(
    val time: String,
    val endTime: String,
    val name: String,
    val level: String,
    val room: String,
    val students: Int,
    val maxStudents: Int,
    val color: Color,
    val isNow: Boolean = false
)

private val TODAY_CLASSES = listOf(
    TodayClass("10:00", "11:30", "Merengue Básico",  "Principiante", "Sala A", 8,  12, SuccessGreen),
    TodayClass("14:00", "15:30", "Salsa On2",         "Intermedio",   "Sala B", 12, 15, ElectricCoral, isNow = true),
    TodayClass("18:00", "19:30", "Salsa Cubana",      "Intermedio",   "Sala A", 15, 15, AmberGold),
    TodayClass("19:30", "21:00", "Bachata Sensual",   "Avanzado",     "Sala B", 10, 12, IndigoAccent)
)

@Composable
fun InstructorDashboard(onNavigateToAttendance: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // ── Header ──
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Buenos días,", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                    Text("Andrés 👋", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Text("Jueves, 12 de Marzo", fontSize = 13.sp, color = TextSecondary)
                }
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(CoralBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text("AG", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ElectricCoral)
                }
            }
        }

        // ── Stats ──
        item {
            Spacer(Modifier.height(2.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem("4", "Clases hoy", ElectricCoral)
                Box(modifier = Modifier.width(1.dp).height(32.dp).background(BorderColor))
                StatItem("45", "Alumnos hoy", IndigoAccent)
                Box(modifier = Modifier.width(1.dp).height(32.dp).background(BorderColor))
                StatItem("92%", "Asistencia", SuccessGreen)
            }
        }

        // ── Active class card ──
        item {
            Spacer(Modifier.height(16.dp))
            val active = TODAY_CLASSES.first { it.isNow }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = ElectricCoral),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.White.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text("EN CURSO", fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, color = Color.White, letterSpacing = 1.sp)
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(active.name, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                            Text("${active.level} • ${active.time} – ${active.endTime}", fontSize = 13.sp, color = Color.White.copy(alpha = 0.8f))
                        }
                        Text("💃", fontSize = 36.sp)
                    }
                    Spacer(Modifier.height(14.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("🚪", fontSize = 13.sp)
                            Text(active.room, fontSize = 13.sp, color = Color.White.copy(alpha = 0.9f), fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.width(8.dp))
                            Text("👥", fontSize = 13.sp)
                            Text("${active.students}/${active.maxStudents}", fontSize = 13.sp, color = Color.White.copy(alpha = 0.9f), fontWeight = FontWeight.SemiBold)
                        }
                        Button(
                            onClick = onNavigateToAttendance,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = ElectricCoral),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            elevation = ButtonDefaults.buttonElevation(0.dp)
                        ) {
                            Text("Asistencia", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // ── Section header ──
        item {
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Resto del día", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("Ver todo →", fontSize = 13.sp, color = ElectricCoral, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {})
            }
        }

        items(TODAY_CLASSES.filter { !it.isNow }) { cls ->
            ClassRowCard(cls)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StatItem(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = color)
        Text(label, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun ClassRowCard(cls: TodayClass) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(cls.color)
            )
            Column(Modifier.weight(1f)) {
                Text(cls.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("${cls.level} • ${cls.time}", fontSize = 12.sp, color = TextSecondary)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("${cls.students}/${cls.maxStudents}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("alumnos", fontSize = 11.sp, color = TextSecondary)
            }
        }
    }
}
