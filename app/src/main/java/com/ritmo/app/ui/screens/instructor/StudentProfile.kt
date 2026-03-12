package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

private val AVATAR_COLORS_SP = listOf(
    Color(0xFF6366F1), Color(0xFF50CD89), Color(0xFFFF5236), Color(0xFFF5A623),
    Color(0xFF009EF7), Color(0xFFEC4899), Color(0xFF14B8A6)
)

private val ATTENDANCE_HISTORY = listOf(
    true, true, false, true, true, true, false, true, true, true,
    false, true, true, true, false, true, true, true, true, false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentProfile(studentName: String, onBack: (() -> Unit)? = null) {
    val initials = studentName.split(" ").take(2).joinToString("") { it.first().uppercase() }
    val avatarColor = AVATAR_COLORS_SP[studentName.length % AVATAR_COLORS_SP.size]
    val attendedCount = ATTENDANCE_HISTORY.count { it }
    val pct = (attendedCount * 100 / ATTENDANCE_HISTORY.size)

    Scaffold(
        containerColor = LightBackground,
        topBar = {
            TopAppBar(
                title = { Text("Perfil de Alumno", fontWeight = FontWeight.Bold, fontSize = 16.sp) },
                navigationIcon = {
                    if (onBack != null) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás", tint = TextPrimary)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceLight),
                windowInsets = WindowInsets(0.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // ── Profile header ──
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(avatarColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(initials, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                }
                Spacer(Modifier.height(12.dp))
                Text(studentName, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                Spacer(Modifier.height(8.dp))
                // Membership badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(IndigoBg)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text("Membresía Premium", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = IndigoAccent)
                }
            }

            Spacer(Modifier.height(2.dp))

            // ── Stats row ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat("$pct%", "Asistencia", SuccessGreen)
                Box(modifier = Modifier.width(1.dp).height(36.dp).background(BorderColor))
                ProfileStat("$attendedCount", "Clases asistidas", ElectricCoral)
                Box(modifier = Modifier.width(1.dp).height(36.dp).background(BorderColor))
                ProfileStat("Intermedio", "Nivel", IndigoAccent)
            }

            Spacer(Modifier.height(12.dp))

            // ── Attendance history card ──
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Historial de Asistencia", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Últimas ${ATTENDANCE_HISTORY.size} clases", fontSize = 12.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(14.dp))

                    // Progress bar
                    LinearProgressIndicator(
                        progress = { pct / 100f },
                        modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                        color = if (pct >= 75) SuccessGreen else if (pct >= 50) AmberGold else DangerRed,
                        trackColor = BorderColor
                    )
                    Spacer(Modifier.height(14.dp))

                    // Calendar dots
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        ATTENDANCE_HISTORY.chunked(5).forEach { week ->
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                week.forEach { attended ->
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (attended) SuccessBg else DangerBg),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            if (attended) "✓" else "✗",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (attended) SuccessGreen else DangerRed
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Info card ──
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Información General", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    InfoRow(label = "Correo", value = "${studentName.lowercase().replace(" ", ".")}@email.com")
                    InfoRow(label = "Teléfono", value = "+1 (555) 000-0000")
                    InfoRow(label = "Inscrito desde", value = "Enero 2024")
                    InfoRow(label = "Clase principal", value = "Salsa On2")
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ProfileStat(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = color)
        Text(label, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
        Text(value, fontSize = 13.sp, color = TextPrimary, fontWeight = FontWeight.SemiBold)
    }
}
