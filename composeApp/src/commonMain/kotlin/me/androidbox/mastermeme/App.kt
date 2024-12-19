package me.androidbox.mastermeme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.androidbox.mastermeme.presentation.EditorScreen
import me.androidbox.mastermeme.presentation.MemeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MemeScreen(
            modifier = Modifier.fillMaxSize(),
            onClickMeme = {
                println(it)
            }
        )
//        EditorScreen(modifier = Modifier.fillMaxSize())
    }
}


