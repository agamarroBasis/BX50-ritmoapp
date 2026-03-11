package com.ritmo.app.ui.screens.instructor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ritmo.app.ui.components.RitmoPrimaryButton
import com.ritmo.app.ui.theme.ElectricCoral
import com.ritmo.app.ui.theme.SurfaceHighlightLight

data class Student(val id: Int, val name: String, var isPresent: Boolean? = null)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(onBackClicked: () -> Unit) {
    val students = remember { mutableStateListOf(
        Student(1, "Maria Garcia"),
        Student(2, "Juan Perez"),
        Student(3, "Sofia Rodriguez"),
        Student(4, "Carlos Lopez"),
        Student(5, "Ana Martinez"),
        Student(6, "Luis Fernandez")
    )}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asistencia: Salsa Cubana") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atras")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(16.dp)) {
                RitmoPrimaryButton("Guardar Asistencia", onClick = { onBackClicked() })
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 80.dp) // space for bottom button
        ) {
            items(students) { student ->
                StudentAttendanceRow(
                    student = student,
                    onStatusChange = { isPresent ->
                        val index = students.indexOfFirst { it.id == student.id }
                        if (index != -1) {
                            students[index] = students[index].copy(isPresent = isPresent)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun StudentAttendanceRow(student: Student, onStatusChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceHighlightLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
            
            Text(
                text = java.lang.StringBuilder().append(student.name).toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            
            // Present Toggle
            IconToggleButton(
                checked = student.isPresent == true,
                onCheckedChange = { onStatusChange(true) }
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Presente",
                    tint = if (student.isPresent == true) ElectricCoral else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Absent Toggle
            IconToggleButton(
                checked = student.isPresent == false,
                onCheckedChange = { onStatusChange(false) }
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Ausente",
                    tint = if (student.isPresent == false) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
