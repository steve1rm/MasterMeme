package me.androidbox.mastermeme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import mastermeme.composeapp.generated.resources.ajtl_46
import me.androidbox.mastermeme.presentation.MemeEditScreen
import me.androidbox.mastermeme.presentation.MemeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable
@Preview
fun App() {
    MaterialTheme {
       /* MemeScreen(
            modifier = Modifier.fillMaxSize(),
            onClickMeme = { memeRes ->

            })*/

        MemeEditScreen(
            modifier = Modifier.fillMaxSize(),
            imageRes = mastermeme.composeapp.generated.resources.Res.drawable.ajtl_46
        )
    }
}


