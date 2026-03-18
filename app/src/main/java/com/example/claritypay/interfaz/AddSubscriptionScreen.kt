package com.example.claritypay.interfaz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubscriptionScreen(
    onBackPressed: () -> Unit,
    onSave: (String, Double, String) -> Unit,
    onCalendarClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar suscripción") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp)
            )

            OutlinedTextField(
                value = cost, onValueChange = { cost = it },
                label = { Text("costo Mensual") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp)
            )

            OutlinedTextField(
                value = date, onValueChange = { date = it },
                label = { Text("Fecha (ej. 17-03)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp),
                trailingIcon = {
                    Icon(Icons.Default.CalendarMonth, contentDescription = "Calendario")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (name.isNotEmpty() && cost.isNotEmpty()) {
                        onSave(name, cost.toDoubleOrNull() ?: 0.0, date)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(55.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Guardar Suscripción")
            }
        }
    }
}