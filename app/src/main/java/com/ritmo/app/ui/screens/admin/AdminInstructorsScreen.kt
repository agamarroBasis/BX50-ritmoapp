package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.theme.ElectricCoral

@Composable
fun AdminInstructorsScreen() {
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
                text = "Gestión de Instructores",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(onClick = { /* TODO: Add instructor */ }, colors = ButtonDefaults.buttonColors(containerColor = ElectricCoral)) {
                Text("+ Nuevo")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Instructors List Placeholder
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InstructorItem("Andrés", "Salsa Cubana", "Activo")
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                InstructorItem("Laura", "Bachata Sensual", "Activo")
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                InstructorItem("Miguel", "Kizomba", "Inactivo")
            }
        }
    }
}

@Composable
fun InstructorItem(name: String, classes: String, status: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.weight(2f)) {
            Text(text = name, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
            Text(text = classes, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(text = status, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f), color = if (status == "Activo") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
    }
}
