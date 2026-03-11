package com.ritmo.app.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritmo.app.ui.theme.ElectricCoral
import com.ritmo.app.ui.theme.SurfaceLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Admin") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Title/Logo Area
        Text(
            text = "RITMO",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 4.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "ACADEMIA DE BAILE",
            style = MaterialTheme.typography.labelLarge.copy(letterSpacing = 2.sp),
            color = ElectricCoral
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Role Selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceLight, RoundedCornerShape(12.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val roles = listOf("Instructor", "Admin")
            roles.forEach { role ->
                val isSelected = selectedRole == role
                Button(
                    onClick = { selectedRole = role },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) ElectricCoral else androidx.compose.ui.graphics.Color.Transparent,
                        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(text = role, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Input Fields
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ElectricCoral,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ElectricCoral,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Login Button
        Button(
            onClick = { onLoginSuccess(selectedRole) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ElectricCoral),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "INICIAR SESIÓN",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
