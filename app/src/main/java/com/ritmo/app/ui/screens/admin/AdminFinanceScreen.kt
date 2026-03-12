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

private data class Payment(val name: String, val initials: String, val color: Color, val amount: String, val date: String, val status: String)

private val PAYMENTS = listOf(
    Payment("Maria Garcia",   "MG", Color(0xFF6366F1), "\$120", "12 mar", "Pagado"),
    Payment("Juan Pérez",     "JP", Color(0xFF50CD89), "\$80",  "11 mar", "Pagado"),
    Payment("Luis Fernández", "LF", Color(0xFFEC4899), "\$120", "10 mar", "Pendiente"),
    Payment("Valentina Cruz", "VC", Color(0xFF14B8A6), "\$60",  "09 mar", "Pagado"),
    Payment("Diego Herrera",  "DH", Color(0xFF6366F1), "\$120", "08 mar", "Vencido"),
    Payment("Ana Martínez",   "AM", Color(0xFF009EF7), "\$80",  "07 mar", "Pendiente")
)

@Composable
fun AdminFinanceScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(LightBackground),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            // Header
            Column(
                modifier = Modifier.fillMaxWidth().background(SurfaceLight).padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text("Finanzas", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                Text("Marzo 2026", fontSize = 13.sp, color = TextSecondary)
            }
            Spacer(Modifier.height(10.dp))
        }

        // Revenue summary cards
        item {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FinanceSummaryCard("\$8,430", "Ingresos", "↑ +12%", SuccessGreen, SuccessBg, Modifier.weight(1f))
                FinanceSummaryCard("\$2,150", "Gastos",   "↓ −5%",  DangerRed,   DangerBg,  Modifier.weight(1f))
            }
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = IndigoBg),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Beneficio neto del mes", fontSize = 13.sp, color = IndigoAccent, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(4.dp))
                    Text("\$6,280", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = IndigoAccent)
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { 0.74f },
                        modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                        color = IndigoAccent,
                        trackColor = BorderColor
                    )
                    Spacer(Modifier.height(4.dp))
                    Text("74% de la meta mensual (\$8,500)", fontSize = 11.sp, color = IndigoAccent.copy(alpha = 0.7f))
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        // Payments header
        item {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Pagos Recientes", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("Ver todos →", fontSize = 12.sp, color = IndigoAccent, fontWeight = FontWeight.SemiBold)
            }
        }

        items(PAYMENTS.size) { i ->
            val p = PAYMENTS[i]
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
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(p.color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(p.initials, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Column(Modifier.weight(1f)) {
                        Text(p.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Text(p.date, fontSize = 12.sp, color = TextSecondary)
                    }
                    Text(p.amount, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Spacer(Modifier.width(4.dp))
                    PaymentChip(p.status)
                }
            }
            Spacer(Modifier.height(6.dp))
        }
    }
}

@Composable
private fun FinanceSummaryCard(value: String, label: String, delta: String, color: Color, bg: Color, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(label, fontSize = 12.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(4.dp))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
            Box(
                modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(bg).padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(delta, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
            }
        }
    }
}

@Composable
private fun PaymentChip(status: String) {
    val (bg, fg) = when (status) {
        "Pagado"   -> Pair(SuccessBg, SuccessGreen)
        "Pendiente"-> Pair(WarningBg, WarningAmber)
        else       -> Pair(DangerBg,  DangerRed)
    }
    Box(
        modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(bg).padding(horizontal = 7.dp, vertical = 3.dp)
    ) {
        Text(status, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = fg)
    }
}
