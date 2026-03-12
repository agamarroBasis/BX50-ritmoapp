package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.background
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

private data class InstructorData(
    val name: String, val initials: String, val avatarColor: Color,
    val specialty: String, val classes: Int, val students: Int, val status: String
)

private val INSTRUCTORS = listOf(
    InstructorData("Andrés García",  "AG", Color(0xFF6366F1), "Salsa · Bachata · Merengue", 4, 45, "Activo"),
    InstructorData("Laura Vásquez", "LV", Color(0xFF50CD89), "Bachata · Kizomba",           3, 32, "Activo"),
    InstructorData("Miguel Torres",  "MT", Color(0xFFFF5236), "Salsa On2 · Jazz",            2, 18, "Inactivo"),
    InstructorData("Sofia Reyes",    "SR", Color(0xFFF5A623), "Ballet · Contemporáneo",      3, 28, "Activo")
)

@Composable
fun AdminInstructorsScreen() {
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
                    Text("Instructores", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Text("${INSTRUCTORS.size} instructores registrados", fontSize = 13.sp, color = TextSecondary)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = IndigoAccent),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text("+ Nuevo", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        items(INSTRUCTORS) { inst ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(
                            modifier = Modifier.size(48.dp).clip(CircleShape).background(inst.avatarColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(inst.initials, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Column(Modifier.weight(1f)) {
                            Text(inst.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Text(inst.specialty, fontSize = 12.sp, color = TextSecondary, lineHeight = 16.sp)
                        }
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(7.dp))
                                .background(if (inst.status == "Activo") SuccessBg else DangerBg)
                                .padding(horizontal = 9.dp, vertical = 3.dp)
                        ) {
                            Text(inst.status, fontSize = 11.sp, fontWeight = FontWeight.Bold,
                                color = if (inst.status == "Activo") SuccessGreen else DangerRed)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = BorderColor)
                    Spacer(Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        InstructorStat("${inst.classes}", "Clases/sem", IndigoAccent)
                        Box(Modifier.width(1.dp).height(28.dp).background(BorderColor))
                        InstructorStat("${inst.students}", "Alumnos", ElectricCoral)
                        Box(Modifier.width(1.dp).height(28.dp).background(BorderColor))
                        InstructorStat("4.9 ★", "Rating", WarningAmber)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun InstructorStat(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = color)
        Text(label, fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
    }
}
