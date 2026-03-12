package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.*

private data class AdminStudent(
    val name: String, val initials: String, val avatarColor: Color,
    val level: String, val membership: String, val status: String
)

private val STUDENTS = listOf(
    AdminStudent("Maria Garcia",    "MG", Color(0xFF6366F1), "Intermedio",   "Premium", "Activo"),
    AdminStudent("Juan Pérez",      "JP", Color(0xFF50CD89), "Principiante", "Básica",  "Activo"),
    AdminStudent("Sofia Rodriguez", "SR", Color(0xFFFF5236), "Avanzado",     "Premium", "Activo"),
    AdminStudent("Carlos López",    "CL", Color(0xFFF5A623), "Principiante", "Prueba",  "Activo"),
    AdminStudent("Ana Martínez",    "AM", Color(0xFF009EF7), "Intermedio",   "Básica",  "Inactivo"),
    AdminStudent("Luis Fernández",  "LF", Color(0xFFEC4899), "Avanzado",     "Premium", "Activo"),
    AdminStudent("Valentina Cruz",  "VC", Color(0xFF14B8A6), "Principiante", "Prueba",  "Activo"),
    AdminStudent("Diego Herrera",   "DH", Color(0xFF6366F1), "Intermedio",   "Premium", "Activo")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminStudentsScreen() {
    var search by remember { mutableStateOf("") }
    val filtered = STUDENTS.filter { it.name.contains(search, ignoreCase = true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Alumnos", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                        Text("${STUDENTS.size} alumnos registrados", fontSize = 13.sp, color = TextSecondary)
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

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = { Text("Buscar por nombre...", color = TextTertiary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IndigoAccent,
                        unfocusedBorderColor = BorderColor
                    )
                )
            }
        }

        // Stats summary
        item {
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf(
                    Triple("124", "Total", IndigoAccent),
                    Triple("98",  "Activos", SuccessGreen),
                    Triple("14",  "En prueba", WarningAmber),
                    Triple("12",  "Inactivos", DangerRed)
                ).forEach { (v, l, c) ->
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(v, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = c)
                            Text(l, fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        items(filtered) { student ->
            StudentCard(student)
            Spacer(Modifier.height(6.dp))
        }
    }
}

@Composable
private fun StudentCard(s: AdminStudent) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
                    .background(s.avatarColor),
                contentAlignment = Alignment.Center
            ) {
                Text(s.initials, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Column(Modifier.weight(1f)) {
                Text(s.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(s.level, fontSize = 12.sp, color = TextSecondary)
            }

            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                MembershipChip(s.membership)
                StatusChip(s.status)
            }
        }
    }
}

@Composable
private fun MembershipChip(membership: String) {
    val (bg, fg) = when (membership) {
        "Premium" -> Pair(IndigoBg, IndigoAccent)
        "Básica"  -> Pair(InfoBg, InfoBlue)
        else      -> Pair(WarningBg, WarningAmber)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .padding(horizontal = 7.dp, vertical = 2.dp)
    ) {
        Text(membership, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = fg)
    }
}

@Composable
private fun StatusChip(status: String) {
    val (bg, fg) = if (status == "Activo") Pair(SuccessBg, SuccessGreen) else Pair(DangerBg, DangerRed)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .padding(horizontal = 7.dp, vertical = 2.dp)
    ) {
        Text(status, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = fg)
    }
}
