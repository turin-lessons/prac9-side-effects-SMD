package com.example.side_effects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Tashqi obyekt
object MockAnalytics {
    var userType: String = "Guest"
}

@Composable
fun Task4Screen() {
    var userType by remember { mutableStateOf("Guest") }
    var sideEffectRunCount by remember { mutableIntStateOf(0) }

    SideEffect {
        sideEffectRunCount++
        MockAnalytics.userType = userType
        Log.d("SideEffectTest", "SideEffect ran: $sideEffectRunCount times, userType: $userType")
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("User Type: $userType")
        Text("SideEffect run count: $sideEffectRunCount")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { userType = if (userType == "Guest") "Premium" else "Guest" }) {
            Text("User Type'ni o'zgartirish")
        }
    }
}