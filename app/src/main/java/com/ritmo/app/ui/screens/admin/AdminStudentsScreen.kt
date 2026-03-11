package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.theme.ElectricCoral

@Composable
fun AdminStudentsScreen() {
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
                text = "Gestión de Alumnos",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(onClick = { /* TODO: Add student */ }, colors = ButtonDefaults.buttonColors(containerColor = ElectricCoral)) {
                Text("+ Nuevo")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Search bar placeholder
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Buscar alumno...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Students Table Placeholder
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Nombre", style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(2f))
                    Text(text = "Clase", style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(1f))
                    Text(text = "Estado", style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(1f))
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                StudentRow("Juan Pérez", "Salsa", "Activo")
                StudentRow("María Gómez", "Bachata", "Inactivo")
                StudentRow("Carlos López", "Ballet", "Activo")
            }
        }
    }
}

@Composable
fun StudentRow(name: String, className: String, status: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = name, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(2f))
        Text(text = className, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(text = status, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f), color = if (status == "Activo") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
    }
}
