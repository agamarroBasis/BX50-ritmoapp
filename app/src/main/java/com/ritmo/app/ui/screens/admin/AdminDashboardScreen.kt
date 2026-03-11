package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.theme.ElectricCoral

@Composable
fun AdminDashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Resumen del Mes",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        // KPI Cards Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { KpiCard(title = "Alumnos Totales", value = "124") }
            item { KpiCard(title = "Membresías Activas", value = "98") }
            item { KpiCard(title = "Ingresos del Mes", value = "$4,200", isPrimary = true) }
            item { KpiCard(title = "Clases Hoy", value = "5") }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Actividad Reciente",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Recent Activity Mock List
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Pago recibido: Juan Pérez ($50)", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Nuevo alumno registrado: María Gómez", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Clase completada: Salsa Principiantes", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun KpiCard(title: String, value: String, isPrimary: Boolean = false) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isPrimary) ElectricCoral.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = if (isPrimary) ElectricCoral else MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
