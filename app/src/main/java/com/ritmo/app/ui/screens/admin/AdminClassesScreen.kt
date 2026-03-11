package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.theme.ElectricCoral

@Composable
fun AdminClassesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Gestión de Clases",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(onClick = { /* TODO: Create class */ }, colors = ButtonDefaults.buttonColors(containerColor = ElectricCoral)) {
                Text("+ Nueva")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Classes List Placeholder
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ClassItem("Salsa Cubana", "Lunes 18:00", "Andrés", "Sala A")
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ClassItem("Bachata", "Martes 19:30", "Laura", "Sala B")
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ClassItem("Ballet Infantil", "Miércoles 16:00", "Sofía", "Sala C")
            }
        }
    }
}

@Composable
fun ClassItem(name: String, time: String, instructor: String, room: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "$time - $room", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = "Prof: $instructor", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
