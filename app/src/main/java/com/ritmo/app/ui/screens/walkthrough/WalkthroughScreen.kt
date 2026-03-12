package com.ritmo.app.ui.screens.walkthrough

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.*
import kotlinx.coroutines.launch

private data class WalkthroughPage(
    val title: String,
    val subtitle: String,
    val gradientStart: Color,
    val gradientEnd: Color,
    val accentColor: Color
)

private val PAGES = listOf(
    WalkthroughPage(
        title = "Bienvenido a Ritmo",
        subtitle = "Tu academia de baile en la palma de tu mano. Gestiona clases, alumnos y mucho más.",
        gradientStart = Color(0xFFFF5236),
        gradientEnd = Color(0xFFFF8A65),
        accentColor = ElectricCoral
    ),
    WalkthroughPage(
        title = "Consulta tu Horario",
        subtitle = "Ve tus clases del día de un vistazo: sala, nivel y cuántos alumnos te esperan.",
        gradientStart = Color(0xFF6366F1),
        gradientEnd = Color(0xFF818CF8),
        accentColor = IndigoAccent
    ),
    WalkthroughPage(
        title = "Toma Asistencia",
        subtitle = "Marca presentes y ausentes en segundos. El historial se guarda automáticamente.",
        gradientStart = Color(0xFF00B97B),
        gradientEnd = Color(0xFF00C9A7),
        accentColor = Color(0xFF00B97B)
    ),
    WalkthroughPage(
        title = "Sigue el Progreso",
        subtitle = "Visualiza el avance de cada alumno e identifica quién necesita más atención.",
        gradientStart = Color(0xFFF59E0B),
        gradientEnd = Color(0xFFFCD34D),
        accentColor = Color(0xFFF59E0B)
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WalkthroughScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState { PAGES.size }
    val scope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == PAGES.lastIndex
    val current = PAGES[pagerState.currentPage]

    Column(modifier = Modifier.fillMaxSize().background(LightBackground)) {

        // ── Illustration area (62% height) ──
        Box(modifier = Modifier.fillMaxWidth().weight(0.62f)) {
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                val p = PAGES[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.linearGradient(listOf(p.gradientStart, p.gradientEnd))),
                    contentAlignment = Alignment.Center
                ) {
                    // Decorative bg circles
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = (-40).dp, y = (-40).dp)
                            .size(160.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.08f))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = 30.dp, y = 30.dp)
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.08f))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(x = (-20).dp, y = 20.dp)
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.06f))
                    )

                    // Illustration content
                    when (page) {
                        0 -> BrandIllustration()
                        1 -> ScheduleIllustration()
                        2 -> AttendanceIllustration()
                        else -> ProgressIllustration()
                    }
                }
            }

            // Page counter pill (top-left)
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.22f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    "${pagerState.currentPage + 1} / ${PAGES.size}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Skip button (top-right)
            Text(
                text = "Omitir",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(alpha = 0.9f),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
                    .clickable { onFinished() }
            )
        }

        // ── Bottom content card (38% height) ──
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.38f)
                .background(SurfaceLight)
                .padding(horizontal = 28.dp)
                .padding(top = 28.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().weight(1f),
                userScrollEnabled = false
            ) { page ->
                val p = PAGES[page]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = p.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = p.subtitle,
                        fontSize = 14.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }

            // Animated pill dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 18.dp)
            ) {
                repeat(PAGES.size) { i ->
                    val selected = i == pagerState.currentPage
                    Box(
                        modifier = Modifier
                            .size(if (selected) 28.dp else 8.dp, 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (selected) current.accentColor else BorderColor)
                    )
                }
            }

            Button(
                onClick = {
                    if (isLastPage) onFinished()
                    else scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = current.accentColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = if (isLastPage) "¡Empezar ahora!" else "Siguiente →",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ── Page 0: Brand ──
@Composable
private fun BrandIllustration() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.22f)),
            contentAlignment = Alignment.Center
        ) {
            Text("💃", fontSize = 64.sp)
        }
        Spacer(Modifier.height(20.dp))
        Text(
            "RITMO",
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            color = Color.White,
            letterSpacing = 8.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(
            "ACADEMIA DE BAILE",
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.75f),
            letterSpacing = 3.sp
        )
        Spacer(Modifier.height(28.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatPill("124", "Alumnos")
            StatPill("6", "Clases/día")
            StatPill("4", "Instructores")
        }
    }
}

@Composable
private fun StatPill(value: String, label: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.18f))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
        Text(label, fontSize = 10.sp, color = Color.White.copy(alpha = 0.8f))
    }
}

// ── Page 1: Schedule mockup ──
@Composable
private fun ScheduleIllustration() {
    Card(
        modifier = Modifier.padding(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Clases de Hoy", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
            Text("Jueves, 13 de Marzo", fontSize = 11.sp, color = TextSecondary)
            Spacer(Modifier.height(14.dp))
            MiniClassRow(Color(0xFF6366F1), "18:00", "Bachata Moderna", "8 alumnos")
            Spacer(Modifier.height(8.dp))
            MiniClassRow(Color(0xFFFF5236), "19:30", "Salsa Cubana", "15 alumnos")
            Spacer(Modifier.height(8.dp))
            MiniClassRow(Color(0xFF50CD89), "10:00", "Merengue Básico", "12 alumnos")
        }
    }
}

@Composable
private fun MiniClassRow(color: Color, time: String, name: String, students: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color.copy(alpha = 0.07f))
            .padding(horizontal = 10.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(color)
        )
        Column(Modifier.weight(1f)) {
            Text(name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(students, fontSize = 10.sp, color = TextSecondary)
        }
        Text(time, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = color)
    }
}

// ── Page 2: Attendance mockup ──
@Composable
private fun AttendanceIllustration() {
    Card(
        modifier = Modifier.padding(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Bachata Moderna", fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Text("18:00 – 19:30 • Sala B", fontSize = 10.sp, color = TextSecondary)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SuccessBg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text("5 / 8", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
                }
            }
            Spacer(Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { 0.625f },
                modifier = Modifier.fillMaxWidth().height(5.dp).clip(RoundedCornerShape(3.dp)),
                color = Color(0xFF00B97B),
                trackColor = BorderColor
            )
            Spacer(Modifier.height(14.dp))
            MiniAttendanceRow("MG", Color(0xFF6366F1), "Maria Garcia", true)
            Spacer(Modifier.height(8.dp))
            MiniAttendanceRow("JP", Color(0xFF50CD89), "Juan Pérez", true)
            Spacer(Modifier.height(8.dp))
            MiniAttendanceRow("LF", Color(0xFFEC4899), "Luis Fernández", false)
        }
    }
}

@Composable
private fun MiniAttendanceRow(initials: String, color: Color, name: String, present: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier.size(30.dp).clip(CircleShape).background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(initials, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Text(name, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary, modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(if (present) SuccessBg else DangerBg),
            contentAlignment = Alignment.Center
        ) {
            Text(
                if (present) "✓" else "✗",
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (present) SuccessGreen else DangerRed
            )
        }
    }
}

// ── Page 3: Progress mockup ──
@Composable
private fun ProgressIllustration() {
    Card(
        modifier = Modifier.padding(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier.size(44.dp).clip(CircleShape).background(Color(0xFF6366F1)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("MG", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Column {
                    Text("Maria Garcia", fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(IndigoBg)
                            .padding(horizontal = 7.dp, vertical = 2.dp)
                    ) {
                        Text("Intermedio", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = IndigoAccent)
                    }
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                MiniStat("92%", "Asistencia", SuccessGreen)
                Box(Modifier.width(1.dp).height(28.dp).background(BorderColor))
                MiniStat("24", "Clases", IndigoAccent)
                Box(Modifier.width(1.dp).height(28.dp).background(BorderColor))
                MiniStat("★ 4.9", "Rating", Color(0xFFF59E0B))
            }
            Spacer(Modifier.height(14.dp))
            MiniProgressBar("Asistencia", 0.92f, SuccessGreen)
            Spacer(Modifier.height(7.dp))
            MiniProgressBar("Puntualidad", 0.78f, IndigoAccent)
            Spacer(Modifier.height(7.dp))
            MiniProgressBar("Participación", 0.85f, Color(0xFFF59E0B))
        }
    }
}

@Composable
private fun MiniStat(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = color)
        Text(label, fontSize = 9.sp, color = TextSecondary)
    }
}

@Composable
private fun MiniProgressBar(label: String, progress: Float, color: Color) {
    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, fontSize = 10.sp, color = TextSecondary)
            Text("${(progress * 100).toInt()}%", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = color)
        }
        Spacer(Modifier.height(3.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
            color = color,
            trackColor = BorderColor
        )
    }
}
