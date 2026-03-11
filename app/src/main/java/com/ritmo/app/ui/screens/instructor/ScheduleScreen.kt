package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.theme.SurfaceDark

@Composable
fun ScheduleScreen() {
    val days = listOf("LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO")
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("MI HORARIO", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(days) { day ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (day == "LUNES") MaterialTheme.colorScheme.primary else SurfaceDark,
                        contentColor = if (day == "LUNES") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(day, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), style = MaterialTheme.typography.labelLarge)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        ClassCardMock(name = "Salsa Cubana", time = "18:00 - 19:30", type = "Intermedio", room = "Sala A")
        Spacer(modifier = Modifier.height(16.dp))
        ClassCardMock(name = "Bachata Sensual", time = "19:30 - 21:00", type = "Avanzado", room = "Sala B")
    }
}

@Composable
fun ClassCardMock(name: String, time: String, type: String, room: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(name, style = MaterialTheme.typography.titleLarge)
            Text("$type • $time", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(room, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
