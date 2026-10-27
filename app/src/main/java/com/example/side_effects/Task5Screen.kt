package com.example.side_effects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
fun Task5Screen() {
    val listState = rememberLazyListState()

    // derivedStateOf varianti
    val showButtonDerived = remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    // snapshotFlow orqali analytics event yuborish
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > 0 }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                Log.d("ScrollAnalytics", "User scrolled past the first item!")
            }
    }

    var recompositionCount = 0
    SideEffect {
        recompositionCount++
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            items(100) { index ->
                Text(
                    text = "Element raqami: $index",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (showButtonDerived.value) {
            Button(
                onClick = { /* Scroll to top logic */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("Yuqoriga")
            }
        }
    }
}