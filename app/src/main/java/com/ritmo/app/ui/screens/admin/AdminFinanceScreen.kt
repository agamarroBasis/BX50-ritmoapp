package com.ritmo.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.theme.ElectricCoral

@Composable
fun AdminFinanceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Contabilidad y Finanzas",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Ingresos del Mes", style = MaterialTheme.typography.labelMedium)
                Text(text = "$4,200", style = MaterialTheme.typography.headlineLarge, color = ElectricCoral)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Gastos del Mes", style = MaterialTheme.typography.labelMedium)
                Text(text = "$1,800", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Pagos Recientes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Table Placeholder
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Alumno", style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(1f))
                    Text(text = "Monto", style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(1f))
                    Text(text = "Estado", style = MaterialTheme.typography.labelMedium, modifier = Modifier.weight(1f))
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                PaymentRow("Juan Pérez", "$50", "Pagado")
                PaymentRow("Ana Gómez", "$50", "Pendiente")
                PaymentRow("Luis Martínez", "$100", "Pagado")
            }
        }
    }
}

@Composable
fun PaymentRow(student: String, amount: String, status: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = student, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(text = amount, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(text = status, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f), color = if (status == "Pagado") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
    }
}
