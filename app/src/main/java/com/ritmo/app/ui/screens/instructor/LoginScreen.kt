package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.components.PulsingLogo
import com.ritmo.app.ui.components.RitmoPrimaryButton
import com.ritmo.app.ui.components.RitmoTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Instructor") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PulsingLogo()
        Spacer(modifier = Modifier.height(48.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FilterChip(
                selected = selectedRole == "Instructor",
                onClick = { selectedRole = "Instructor" },
                label = { Text("Instructor") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = selectedRole == "Admin",
                onClick = { selectedRole = "Admin" },
                label = { Text("Admin") }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        RitmoTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        RitmoTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        RitmoPrimaryButton(
            text = "Iniciar Sesión",
            onClick = onLoginSuccess
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = { /* Biometric Login */ }) {
            Text("Login Biométrico", color = MaterialTheme.colorScheme.secondary)
        }
    }
}
