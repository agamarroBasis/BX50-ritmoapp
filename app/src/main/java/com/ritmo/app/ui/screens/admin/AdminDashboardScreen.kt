package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

private data class KpiData(val label: String, val value: String, val delta: String, val emoji: String, val color: Color)
private data class ActivityItem(val text: String, val time: String, val dotColor: Color)

private val KPIS = listOf(
    KpiData("Alumnos Totales",     "124",    "+5 este mes",     "👥", IndigoAccent),
    KpiData("Membresías Activas",  "98",     "+3 esta semana",  "✅", SuccessGreen),
    KpiData("Ingresos (Marzo)",    "\$8,430", "+12%",           "💰", AmberGold),
    KpiData("Clases Hoy",          "6",      "2 en curso",      "📅", InfoBlue)
)

private val ACTIVITY = listOf(
    ActivityItem("Maria Garcia renovó su membresía Premium.", "hace 10 min", SuccessGreen),
    ActivityItem("Nueva clase de Bachata para el viernes.",  "hace 25 min", InfoBlue),
    ActivityItem("Pago pendiente: Luis Fernández (\$120).",    "hace 1h",    WarningAmber),
    ActivityItem("Instructor Carlos actualizó su horario.",  "hace 2h",    IndigoAccent),
    ActivityItem("Ana Martínez canceló su membresía.",       "hace 3h",    DangerRed)
)

@Composable
fun AdminDashboardScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // ── Header ──
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceLight)
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Panel Administrativo", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                    Text("Resumen general", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(IndigoBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text("AG", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = IndigoAccent)
                }
            }
        }

        // ── KPI cards 2x2 grid ──
        item {
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                KPIS.chunked(2).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { kpi ->
                            AdminKpiCard(kpi = kpi, modifier = Modifier.weight(1f))
                        }
                        if (row.size < 2) Spacer(Modifier.weight(1f))
                    }
                }
            }
        }

        // ── Today's classes ──
        item {
            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Clases de Hoy", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text("Ver todo →", fontSize = 12.sp, color = IndigoAccent, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(Modifier.height(12.dp))
                    listOf(
                        Triple("14:00", "Salsa On2 • Intermedio", 0.8f),
                        Triple("16:00", "Merengue Básico • Principiante", 0.55f),
                        Triple("18:00", "Salsa Cubana • Intermedio", 1.0f),
                        Triple("19:30", "Bachata Sensual • Avanzado", 0.65f)
                    ).forEachIndexed { i, (time, name, fill) ->
                        Column {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(time, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = IndigoAccent, modifier = Modifier.width(44.dp))
                                Text(name, fontSize = 13.sp, color = TextPrimary, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                                Text("${(fill * 100).toInt()}%", fontSize = 12.sp, color = if (fill >= 0.9f) DangerRed else SuccessGreen, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { fill },
                                modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                                color = if (fill >= 0.9f) DangerRed else IndigoAccent,
                                trackColor = BorderColor
                            )
                            if (i < 3) Spacer(Modifier.height(10.dp))
                        }
                    }
                }
            }
        }

        // ── Activity feed ──
        item {
            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceLight),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Actividad Reciente", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Spacer(Modifier.height(12.dp))
                    ACTIVITY.forEachIndexed { i, item ->
                        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(item.dotColor)
                            )
                            Text(item.text, fontSize = 13.sp, color = TextPrimary, modifier = Modifier.weight(1f), lineHeight = 19.sp)
                            Text(item.time, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                        }
                        if (i < ACTIVITY.lastIndex) {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, start = 17.dp), color = BorderColor)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdminKpiCard(kpi: KpiData, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(kpi.label, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium, lineHeight = 15.sp, modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(kpi.color.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(kpi.emoji, fontSize = 16.sp)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(kpi.value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
            Text(kpi.delta, fontSize = 11.sp, color = SuccessGreen, fontWeight = FontWeight.SemiBold)
        }
    }
}
