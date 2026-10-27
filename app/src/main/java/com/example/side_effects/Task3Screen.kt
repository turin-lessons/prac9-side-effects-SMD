package com.example.side_effects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun Task3Screen() {
    var showComposable by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = showComposable,
            onCheckedChange = { showComposable = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (showComposable) {
            LifecycleLoggerComponent()
        }
    }
}

@Composable
fun LifecycleLoggerComponent() {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            Log.d("LifecycleLogger", "Event: $event")
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.d("LifecycleLogger", "Observer removed onDispose")
        }
    }

    Text("Lifecycle logger ishlamoqda. Logcat'ni kuzating.")
}