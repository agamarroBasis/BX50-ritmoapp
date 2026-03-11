package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.components.RitmoPrimaryButton
import com.ritmo.app.ui.theme.SurfaceLight

@Composable
fun InstructorDashboard(onNavigateToAttendance: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "BUENOS DIAS,\nANDRES",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "LUNES, 10 MARZO",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceLight),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("SIGUIENTE CLASE", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Salsa Cubana", style = MaterialTheme.typography.headlineLarge)
                Text("Intermedio • 18:00 - 19:30", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text("Sala A", style = MaterialTheme.typography.bodyMedium)
                    Text("15 Alumnos", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        RitmoPrimaryButton(
            text = "Registrar Asistencia",
            onClick = onNavigateToAttendance
        )
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}
