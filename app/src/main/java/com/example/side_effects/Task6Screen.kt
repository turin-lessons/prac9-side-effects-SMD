package com.example.side_effects

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

sealed interface ImageState {
    object Loading : ImageState
    data class Success(val url: String) : ImageState
    object Error : ImageState
}

suspend fun fakeLoadNetworkImage(url: String): ImageState {
    delay(1000L)
    return if (url.endsWith(".png")) {
        ImageState.Success(url)
    } else {
        ImageState.Error
    }
}

@Composable
fun loadNetworkImage(url: String): State<ImageState> {
    return produceState<ImageState>(initialValue = ImageState.Loading, url) {
        try {
            value = fakeLoadNetworkImage(url)
        } finally {
            Log.d("ProduceState", "Producer cancelled or finished for URL: $url")
        }
    }
}

@Composable
fun Task6Screen() {
    var url by remember { mutableStateOf("image.png") }
    val imageState by loadNetworkImage(url)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = imageState) {
            is ImageState.Loading -> CircularProgressIndicator()
            is ImageState.Success -> Text("Muvaffaqiyatli yuklandi: ${state.url}")
            is ImageState.Error -> Text("Xatolik yuz berdi!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            url = if (url == "image.png") "image.jpg" else "image.png"
        }) {
            Text("URL'ni o'zgartirish")
        }
    }
}