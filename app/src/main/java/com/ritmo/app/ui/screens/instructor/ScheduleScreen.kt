package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        ScheduleClass("09:00", "10:30", "Salsa Básica",      "Principiante", "Sala A", 10, Color(0xFF50CD89)),
        ScheduleClass("18:00", "19:30", "Bachata Moderna",   "Intermedio",   "Sala B", 8,  Color(0xFF6366F1))
    ),
    "MAR" to listOf(
        ScheduleClass("16:00", "17:30", "Merengue",          "Principiante", "Sala A", 12, Color(0xFFF5A623)),
        ScheduleClass("19:30", "21:00", "Salsa On2",         "Avanzado",     "Sala B", 9,  Color(0xFFFF5236))
    ),
    "MIE" to listOf(
        ScheduleClass("10:00", "11:30", "Kizomba",           "Intermedio",   "Sala C", 7,  Color(0xFFEC4899))
    ),
    "JUE" to listOf(
        ScheduleClass("14:00", "15:30", "Salsa On2",         "Intermedio",   "Sala B", 12, Color(0xFFFF5236)),
        ScheduleClass("18:00", "19:30", "Salsa Cubana",      "Intermedio",   "Sala A", 15, Color(0xFFF5A623)),
        ScheduleClass("19:30", "21:00", "Bachata Sensual",   "Avanzado",     "Sala B", 10, Color(0xFF6366F1))
    ),
    "VIE" to listOf(
        ScheduleClass("17:00", "18:30", "Salsa Básica",      "Principiante", "Sala A", 14, Color(0xFF50CD89)),
        ScheduleClass("19:00", "20:30", "Merengue Avanzado", "Avanzado",     "Sala C", 6,  Color(0xFF009EF7))
    ),
    "SAB" to listOf(
        ScheduleClass("10:00", "12:00", "Taller Especial",   "Todos",        "Sala A", 20, Color(0xFFFF5236))
    ),
    "DOM" to emptyList()
)

private val DAYS = listOf("LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM")
private val DAY_HEADERS = listOf("L", "M", "X", "J", "V", "S", "D")

// March 2026: day 1 = Sunday
private val MARCH_CALENDAR = listOf(
    listOf(0, 0, 0, 0, 0, 0, 1),
    listOf(2, 3, 4, 5, 6, 7, 8),
    listOf(9, 10, 11, 12, 13, 14, 15),
    listOf(16, 17, 18, 19, 20, 21, 22),
    listOf(23, 24, 25, 26, 27, 28, 29),
    listOf(30, 31, 0, 0, 0, 0, 0)
)

// Maps a day-of-month (March 2026) to its week-day name
private fun dayNameForDate(d: Int): String {
    if (d <= 0 || d > 31) return "DOM"
    return DAYS[(d + 5) % 7]
}

private fun hasClasses(d: Int) =
    d > 0 && (WEEK_SCHEDULE[dayNameForDate(d)] ?: emptyList()).isNotEmpty()

@Composable
fun ScheduleScreen() {
    var selectedDate by remember { mutableStateOf(12) } // today = March 12, 2026
    val selectedDayName = dayNameForDate(selectedDate)
    val classes = WEEK_SCHEDULE[selectedDayName] ?: emptyList()

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(LightBackground),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        // ── Header ──
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text("Mi Horario", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                Text("Marzo 2026", fontSize = 13.sp, color = TextSecondary)
            }
        }

        // ── Month calendar card ──
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp)) {
                    // Month label
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 4.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Marzo 2026",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CalendarLegendDot(ElectricCoral, "Con clases")
                        }
                    }

                    // Day-of-week headers
                    Row(modifier = Modifier.fillMaxWidth()) {
                        DAY_HEADERS.forEach { h ->
                            Text(
                                text = h,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextTertiary
                            )
                        }
                    }
                    Spacer(Modifier.height(6.dp))

                    // Calendar grid rows
                    MARCH_CALENDAR.forEach { week ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            week.forEach { day ->
                                val isSelected = day == selectedDate
                                val isToday = day == 12
                                val hasCls = hasClasses(day)

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .padding(2.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (day > 0) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(CircleShape)
                                                .then(
                                                    when {
                                                        isSelected -> Modifier.background(ElectricCoral)
                                                        isToday -> Modifier.border(2.dp, ElectricCoral, CircleShape)
                                                        else -> Modifier
                                                    }
                                                )
                                                .clickable { selectedDate = day },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = "$day",
                                                    fontSize = 13.sp,
                                                    fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal,
                                                    color = when {
                                                        isSelected -> Color.White
                                                        isToday -> ElectricCoral
                                                        else -> TextPrimary
                                                    },
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                        // Class indicator dot
                                        if (hasCls) {
                                            Box(
                                                modifier = Modifier
                                                    .align(Alignment.BottomCenter)
                                                    .padding(bottom = 2.dp)
                                                    .size(4.dp)
                                                    .clip(CircleShape)
                                                    .background(
                                                        if (isSelected) Color.White.copy(alpha = 0.85f)
                                                        else ElectricCoral
                                                    )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // ── Selected day header ──
        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(CoralBg)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        "$selectedDayName $selectedDate mar",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ElectricCoral
                    )
                }
                Text(
                    if (classes.isEmpty()) "Sin clases"
                    else "${classes.size} clase${if (classes.size > 1) "s" else ""} programada${if (classes.size > 1) "s" else ""}",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        }

        // ── Classes list or empty state ──
        if (classes.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🎉", fontSize = 44.sp)
                        Spacer(Modifier.height(10.dp))
                        Text(
                            "Sin clases este día",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary
                        )
                        Text("Disfruta tu descanso", fontSize = 13.sp, color = TextTertiary)
                    }
                }
            }
        } else {
            items(classes) { cls ->
                ScheduleClassCard(cls)
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun CalendarLegendDot(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(color))
        Text(label, fontSize = 10.sp, color = TextSecondary)
    }
}

@Composable
private fun ScheduleClassCard(cls: ScheduleClass) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
            // Colored accent bar
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
                Box(modifier = Modifier.width(1.dp).height(12.dp).background(BorderColor))
                Text(cls.endTime, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
            }

            Box(modifier = Modifier.width(1.dp).height(40.dp).background(BorderColor))

            // Class info
            Column(Modifier.weight(1f)) {
                Text(cls.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Spacer(Modifier.height(3.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    LevelPill(cls.level, cls.color)
                    Text("• ${cls.room}", fontSize = 12.sp, color = TextSecondary)
                }
            }

            // Student count
            Column(horizontalAlignment = Alignment.End) {
                Text("${cls.students}", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
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
