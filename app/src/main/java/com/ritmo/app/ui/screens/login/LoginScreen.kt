package com.ritmo.app.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Instructor") }

    val isAdmin = selectedRole == "Admin"
    val accentColor = if (isAdmin) IndigoAccent else ElectricCoral
    val accentBg = if (isAdmin) IndigoBg else CoralBg

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(top = 60.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ── Logo ──
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(
                    Brush.linearGradient(
                        listOf(accentColor, accentColor.copy(alpha = 0.75f))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "R", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = "RITMO",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 6.sp,
            color = TextPrimary
        )
        Text(
            text = "Academia de Baile",
            fontSize = 12.sp,
            color = TextSecondary,
            fontWeight = FontWeight.Medium,
            letterSpacing = 2.sp
        )

        Spacer(Modifier.height(36.dp))

        // ── Role Selector ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(BorderColor.copy(alpha = 0.6f))
                .padding(4.dp)
        ) {
            listOf("Instructor", "Admin").forEach { role ->
                val selected = selectedRole == role
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(9.dp))
                        .background(if (selected) SurfaceLight else Color.Transparent)
                        .clickable { selectedRole = role }
                        .padding(vertical = 11.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = role,
                        fontSize = 14.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                        color = if (selected) accentColor else TextSecondary
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── Login Card ──
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceLight),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentColor,
                        focusedLabelColor = accentColor,
                        unfocusedBorderColor = BorderColor
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentColor,
                        focusedLabelColor = accentColor,
                        unfocusedBorderColor = BorderColor
                    )
                )

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 13.sp,
                    color = accentColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {}
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // ── Submit ──
        Button(
            onClick = { onLoginSuccess(selectedRole) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(text = "Iniciar sesión", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))

        // ── Hint box ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(accentBg)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = if (isAdmin)
                    "Acceso restringido al equipo administrativo de Ritmo."
                else
                    "Portal exclusivo para instructores certificados de Ritmo.",
                fontSize = 12.sp,
                color = accentColor.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
