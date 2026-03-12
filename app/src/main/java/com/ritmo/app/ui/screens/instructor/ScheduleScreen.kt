package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

private data class ScheduleClass(
    val time: String,
    val endTime: String,
    val name: String,
    val level: String,
    val room: String,
    val students: Int,
    val color: Color
)

private val WEEK_SCHEDULE = mapOf(
    "LUN" to listOf(
        ScheduleClass("09:00", "10:30", "Salsa Básica",     "Principiante", "Sala A", 10, Color(0xFF50CD89)),
        ScheduleClass("18:00", "19:30", "Bachata Moderna",  "Intermedio",   "Sala B", 8,  Color(0xFF6366F1))
    ),
    "MAR" to listOf(
        ScheduleClass("16:00", "17:30", "Merengue",         "Principiante", "Sala A", 12, Color(0xFFF5A623)),
        ScheduleClass("19:30", "21:00", "Salsa On2",        "Avanzado",     "Sala B", 9,  Color(0xFFFF5236))
    ),
    "MIE" to listOf(
        ScheduleClass("10:00", "11:30", "Kizomba",          "Intermedio",   "Sala C", 7,  Color(0xFFEC4899))
    ),
    "JUE" to listOf(
        ScheduleClass("14:00", "15:30", "Salsa On2",        "Intermedio",   "Sala B", 12, Color(0xFFFF5236)),
        ScheduleClass("18:00", "19:30", "Salsa Cubana",     "Intermedio",   "Sala A", 15, Color(0xFFF5A623)),
        ScheduleClass("19:30", "21:00", "Bachata Sensual",  "Avanzado",     "Sala B", 10, Color(0xFF6366F1))
    ),
    "VIE" to listOf(
        ScheduleClass("17:00", "18:30", "Salsa Básica",     "Principiante", "Sala A", 14, Color(0xFF50CD89)),
        ScheduleClass("19:00", "20:30", "Merengue Avanzado","Avanzado",     "Sala C", 6,  Color(0xFF009EF7))
    ),
    "SAB" to listOf(
        ScheduleClass("10:00", "12:00", "Taller Especial",  "Todos",        "Sala A", 20, Color(0xFFFF5236))
    ),
    "DOM" to emptyList()
)

private val DAYS = listOf("LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM")

@Composable
fun ScheduleScreen() {
    var selectedDay by remember { mutableStateOf("JUE") }
    val classes = WEEK_SCHEDULE[selectedDay] ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // ── Top bar ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceLight)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column {
                Text("Mi Horario", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                Text("Semana actual", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
            }
        }

        // ── Day selector ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceLight)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            DAYS.forEachIndexed { index, day ->
                val selected = day == selectedDay
                val hasClasses = (WEEK_SCHEDULE[day] ?: emptyList()).isNotEmpty()
                val dayNum = 10 + index // mock day numbers: 10–16

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (selected) ElectricCoral else Color.Transparent)
                        .clickable { selectedDay = day }
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = day,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selected) Color.White else TextSecondary,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "$dayNum",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (selected) Color.White else TextPrimary
                    )
                    Spacer(Modifier.height(4.dp))
                    // Dot indicator for days with classes
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    selected && hasClasses -> Color.White
                                    hasClasses -> ElectricCoral
                                    else -> Color.Transparent
                                }
                            )
                    )
                }
            }
        }

        Divider(color = BorderColor, thickness = 1.dp)

        // ── Classes list ──
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (classes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🎉", fontSize = 40.sp)
                        Spacer(Modifier.height(8.dp))
                        Text("Sin clases este día", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary)
                        Text("Disfruta tu descanso", fontSize = 13.sp, color = TextTertiary)
                    }
                }
            } else {
                // Summary chip
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(CoralBg)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        "${classes.size} clase${if (classes.size > 1) "s" else ""} programada${if (classes.size > 1) "s" else ""}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ElectricCoral
                    )
                }

                classes.forEach { cls ->
                    ScheduleClassCard(cls)
                }
            }
        }
    }
}

@Composable
private fun ScheduleClassCard(cls: ScheduleClass) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
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
            // Color accent bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(52.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(cls.color)
            )

            // Time column
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(48.dp)) {
                Text(cls.time, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                Box(modifier = Modifier.width(1.dp).height(14.dp).background(BorderColor))
                Text(cls.endTime, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
            }

            Box(modifier = Modifier.width(1.dp).height(40.dp).background(BorderColor))

            // Info
            Column(Modifier.weight(1f)) {
                Text(cls.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    LevelPill(cls.level, cls.color)
                    Text("• ${cls.room}", fontSize = 12.sp, color = TextSecondary)
                }
            }

            // Student count
            Column(horizontalAlignment = Alignment.End) {
                Text("${cls.students}", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                Text("alumnos", fontSize = 11.sp, color = TextSecondary)
            }
        }
    }
}

@Composable
private fun LevelPill(level: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.12f))
            .padding(horizontal = 7.dp, vertical = 2.dp)
    ) {
        Text(level, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = color)
    }
}
