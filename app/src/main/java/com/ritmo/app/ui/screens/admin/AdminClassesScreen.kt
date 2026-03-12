package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

private data class ClassData(
    val name: String, val instructor: String, val time: String, val day: String,
    val room: String, val students: Int, val max: Int, val level: String,
    val color: Color, val status: String
)

private val CLASSES = listOf(
    ClassData("Salsa Cubana",     "Andrés G.", "18:00–19:30", "Lun/Jue", "Sala A", 15, 15, "Intermedio",   Color(0xFFFF5236), "Llena"),
    ClassData("Bachata Sensual",  "Laura V.",  "19:30–21:00", "Mar/Jue", "Sala B", 10, 12, "Avanzado",     Color(0xFF6366F1), "Abierta"),
    ClassData("Merengue Básico",  "Andrés G.", "10:00–11:30", "Lun/Mie", "Sala A", 8,  12, "Principiante", Color(0xFF50CD89), "Abierta"),
    ClassData("Salsa On2",        "Laura V.",  "14:00–15:30", "Mar/Jue", "Sala B", 12, 15, "Intermedio",   Color(0xFF009EF7), "Abierta"),
    ClassData("Kizomba",          "Miguel T.", "17:00–18:30", "Vie",     "Sala C", 7,  10, "Intermedio",   Color(0xFFEC4899), "Abierta"),
    ClassData("Taller Especial",  "Andrés G.", "10:00–12:00", "Sábado",  "Sala A", 20, 20, "Todos",        Color(0xFFF5A623), "Llena")
)

@Composable
fun AdminClassesScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(LightBackground),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().background(SurfaceLight).padding(horizontal = 20.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Clases", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Text("${CLASSES.size} clases programadas", fontSize = 13.sp, color = TextSecondary)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = IndigoAccent),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text("+ Nueva", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        items(CLASSES) { cls ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
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
                            .width(4.dp)
                            .height(56.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(cls.color)
                    )
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(cls.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            ClassStatusChip(cls.status)
                        }
                        Spacer(Modifier.height(2.dp))
                        Text("${cls.time} • ${cls.day}", fontSize = 12.sp, color = TextSecondary)
                        Text("${cls.instructor} • ${cls.room}", fontSize = 12.sp, color = TextSecondary)
                        Spacer(Modifier.height(6.dp))
                        // Occupancy bar
                        val fill = cls.students.toFloat() / cls.max
                        LinearProgressIndicator(
                            progress = { fill },
                            modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                            color = if (fill >= 1f) DangerRed else cls.color,
                            trackColor = BorderColor
                        )
                        Spacer(Modifier.height(2.dp))
                        Text("${cls.students}/${cls.max} alumnos", fontSize = 11.sp, color = TextSecondary)
                    }
                    // Level pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(cls.color.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(cls.level, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = cls.color)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ClassStatusChip(status: String) {
    val (bg, fg) = if (status == "Llena") Pair(DangerBg, DangerRed) else Pair(SuccessBg, SuccessGreen)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(bg)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(status, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold, color = fg)
    }
}
