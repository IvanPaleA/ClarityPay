package com.example.claritypay.interfaz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.claritypay.data.Subscription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    subscriptions: List<Subscription>,
    onBackPressed: () -> Unit
) {
    val daysInMonth = (1..31).toList()
    val dayNames = listOf("D", "L", "M", "M", "J", "V", "S") //

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Marzo, 2026") }, //
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                dayNames.forEach { day ->
                    Text(day, fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(daysInMonth) { day ->
                    // ver si hay cobro ese dia
                    val hasSubscription = subscriptions.any {
                        it.payDate.startsWith(day.toString().padStart(2, '0'))
                    }

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .border(
                                width = 1.dp,
                                color = if (hasSubscription) Color(0xFF2196F3) else Color.LightGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(
                                color = if (hasSubscription) Color(0xFF2196F3).copy(alpha = 0.1f) else Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = day.toString(), fontSize = 14.sp)
                            if (hasSubscription) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .background(Color(0xFF2196F3), CircleShape)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Leyenda
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(10.dp).background(Color(0xFF2196F3), CircleShape))
                Spacer(modifier = Modifier.width(8.dp))
                Text("• Mensualidad", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}