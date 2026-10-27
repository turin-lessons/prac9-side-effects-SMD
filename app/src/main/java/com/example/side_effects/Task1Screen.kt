package com.example.side_effects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun Task1Screen() {
    var version by remember { mutableIntStateOf(0) }

    // Har 500 ms da version o'zgarib, yangi lambda hosil qiladi
    LaunchedEffect(Unit) {
        while (true) {
            delay(500.milliseconds)
            version++
        }
    }

    val onTimeoutLambda: () -> Unit = {
        Log.d("Task1Splash", "Timeout executed with version: v$version")
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Version: v$version", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LandingScreenDirect(onTimeout = onTimeoutLambda)
            Spacer(modifier = Modifier.height(8.dp))
            LandingScreenWithRememberUpdatedState(onTimeout = onTimeoutLambda)
        }
    }
}

@Composable
fun LandingScreenDirect(onTimeout: () -> Unit) {
    LaunchedEffect(true) {
        delay(2.seconds)
        onTimeout()
    }
    Text("Variant 1 (Direct): 2 sekund kuting...")
}

@Composable
fun LandingScreenWithRememberUpdatedState(onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(true) {
        delay(2.seconds)
        currentOnTimeout()
    }
    Text("Variant 2 (UpdatedState): 2 sekund kuting...")
}