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
import androidx.compose.ui.graphics.Brush
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
        modifier = Modifier.fillMaxSize().background(LightBackground),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        // ── Header with gradient ──
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFF1EE), SurfaceLight)
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                // Decorative circle
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 20.dp, y = (-20).dp)
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(ElectricCoral.copy(alpha = 0.06f))
                )
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Buenos días,", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                        Text("Andrés 👋", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                        Spacer(Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CoralBg)
                                .padding(horizontal = 10.dp, vertical = 3.dp)
                        ) {
                            Text(
                                "Jueves, 12 de Marzo",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = ElectricCoral
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(ElectricCoral),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("AG", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    }
                }
            }
        }

        // ── Stats row ──
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatCard("4", "Clases hoy", "🎯", ElectricCoral, CoralBg, Modifier.weight(1f))
                StatCard("45", "Alumnos", "👥", IndigoAccent, IndigoBg, Modifier.weight(1f))
                StatCard("92%", "Asistencia", "✅", SuccessGreen, SuccessBg, Modifier.weight(1f))
            }
        }

        // ── Active class card ──
        item {
            val active = TODAY_CLASSES.first { it.isNow }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = ElectricCoral),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Decorative bg circle inside card
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 20.dp, y = (-20).dp)
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.08f))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(x = (-15).dp, y = 15.dp)
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.06f))
                    )
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(7.dp))
                                        .background(Color.White.copy(alpha = 0.22f))
                                        .padding(horizontal = 9.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        "● EN CURSO",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White,
                                        letterSpacing = 0.8.sp
                                    )
                                }
                                Spacer(Modifier.height(8.dp))
                                Text(active.name, fontSize = 21.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text(
                                    "${active.level} • ${active.time} – ${active.endTime}",
                                    fontSize = 13.sp,
                                    color = Color.White.copy(alpha = 0.82f)
                                )
                            }
                            Text("💃", fontSize = 40.sp)
                        }
                        Spacer(Modifier.height(14.dp))

                        // Occupancy bar
                        val fill = active.students.toFloat() / active.maxStudents
                        LinearProgressIndicator(
                            progress = { fill },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.25f)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "${active.students}/${active.maxStudents} alumnos • ${active.room}",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Spacer(Modifier.height(14.dp))
                        Button(
                            onClick = onNavigateToAttendance,
                            modifier = Modifier.fillMaxWidth().height(44.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = ElectricCoral),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(0.dp)
                        ) {
                            Text("Registrar Asistencia →", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // ── Section header ──
        item {
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Resto del día", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(
                    "Ver horario →",
                    fontSize = 13.sp,
                    color = ElectricCoral,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {}
                )
            }
        }

        items(TODAY_CLASSES.filter { !it.isNow }) { cls ->
            ClassRowCard(cls)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StatCard(value: String, label: String, emoji: String, color: Color, bg: Color, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(bg),
                contentAlignment = Alignment.Center
            ) {
                Text(emoji, fontSize = 14.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = color)
            Text(label, fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun ClassRowCard(cls: TodayClass) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(44.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(cls.color)
                )
                Column(Modifier.weight(1f)) {
                    Text(cls.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("${cls.level} • ${cls.time} – ${cls.endTime}", fontSize = 12.sp, color = TextSecondary)
                    Text(cls.room, fontSize = 11.sp, color = TextTertiary)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("${cls.students}", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Text("/ ${cls.maxStudents}", fontSize = 11.sp, color = TextSecondary)
                }
            }
            Spacer(Modifier.height(8.dp))
            val fill = cls.students.toFloat() / cls.maxStudents
            LinearProgressIndicator(
                progress = { fill },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = if (fill >= 1f) DangerRed else cls.color,
                trackColor = BorderColor
            )
        }
    }
}
