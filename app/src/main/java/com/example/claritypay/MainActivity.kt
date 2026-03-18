package com.example.claritypay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.claritypay.interfaz.HomeScreen
import com.example.claritypay.ui.ClarityViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.claritypay.interfaz.AddSubscriptionScreen
import com.example.claritypay.interfaz.CalendarScreen
import com.example.claritypay.interfaz.SubscriptionDetailScreen
import com.example.claritypay.ui.theme.ClarityPayTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClarityPayTheme {
                val navController = rememberNavController()
                val viewModel: ClarityViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //definir las pantallas
                    NavHost(navController = navController, startDestination = "home") {

                        //gastos del mes
                        composable("home") {
                            HomeScreen(
                                viewModel = viewModel,
                                onAddClick = { navController.navigate("registro") },
                                onSubClick = { subId -> navController.navigate("detalle/$subId") },
                                onCalendarClick = { navController.navigate("calendario") }
                            )
                        }

                        //agregar suscripción
                        composable("registro") {
                            AddSubscriptionScreen(
                                onBackPressed = { navController.popBackStack() },
                                onSave = { nombre, costo, fecha ->
                                    viewModel.addSubscription(nombre, costo, fecha)
                                    navController.popBackStack()
                                },
                                onCalendarClick = { navController.navigate("calendario") }
                            )
                        }

                        composable("detalle/{subId}") { backStackEntry ->
                            val subId = backStackEntry.arguments?.getString("subId")?.toIntOrNull()
                            val subscription = viewModel.subscriptions.find { it.id == subId }

                            SubscriptionDetailScreen(
                                subscription = subscription,
                                onBackPressed = { navController.popBackStack() }
                            )
                        }

                        composable("calendario") {
                            CalendarScreen(
                                subscriptions = viewModel.subscriptions,
                                onBackPressed = { navController.popBackStack() }
                            )
                        }

                    }
                }
            }
        }
    }
}