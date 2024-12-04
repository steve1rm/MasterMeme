package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemeItem(
    imageRes: DrawableResource
) {
    Image(
        painter = painterResource(resource = imageRes),
        contentDescription = "meme"
    )
}