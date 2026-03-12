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

data class WalkthroughPage(
    val emoji: String,
    val title: String,
    val subtitle: String,
    val gradientStart: Color,
    val gradientEnd: Color
)

private val PAGES = listOf(
    WalkthroughPage(
        emoji = "💃",
        title = "Bienvenido a Ritmo",
        subtitle = "Tu academia de baile en la palma de tu mano. Gestiona clases, alumnos y mucho más.",
        gradientStart = Color(0xFFFF5236),
        gradientEnd = Color(0xFFFF8A65)
    ),
    WalkthroughPage(
        emoji = "📅",
        title = "Organiza tu Horario",
        subtitle = "Visualiza tus clases del día y la semana. Todo tu calendario en un solo lugar.",
        gradientStart = Color(0xFF6366F1),
        gradientEnd = Color(0xFF818CF8)
    ),
    WalkthroughPage(
        emoji = "✅",
        title = "Registra Asistencia",
        subtitle = "Marca la asistencia de tus alumnos rápidamente y mantén un historial completo.",
        gradientStart = Color(0xFF50CD89),
        gradientEnd = Color(0xFF00C9A7)
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WalkthroughScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState { PAGES.size }
    val scope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == PAGES.lastIndex

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        // Illustration area — 62% of screen height
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.62f)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val p = PAGES[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(listOf(p.gradientStart, p.gradientEnd))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Large emoji illustration
                        Box(
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = p.emoji, fontSize = 64.sp)
                        }

                        Spacer(Modifier.height(20.dp))

                        // Brand watermark
                        Text(
                            "RITMO",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White.copy(alpha = 0.4f),
                            letterSpacing = 6.sp
                        )
                    }
                }
            }

            // Skip button top-right
            Text(
                text = "Omitir",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(alpha = 0.85f),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
                    .clickable { onFinished() }
            )
        }

        // Bottom content card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.38f)
                .background(SurfaceLight)
                .padding(horizontal = 28.dp)
                .padding(top = 28.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text content per page
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
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
                        lineHeight = 21.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            // Page indicator dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                repeat(PAGES.size) { i ->
                    val selected = i == pagerState.currentPage
                    Box(
                        modifier = Modifier
                            .size(if (selected) 28.dp else 8.dp, 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (selected) ElectricCoral else BorderColor)
                    )
                }
            }

            // Action button
            Button(
                onClick = {
                    if (isLastPage) {
                        onFinished()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ElectricCoral),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = if (isLastPage) "¡Empezar ahora!" else "Siguiente",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
