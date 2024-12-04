package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemeItem(
    imageRes: DrawableResource,
    onClickMeme: (res: DrawableResource) -> Unit
) {
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClickMeme(imageRes)
            },
        painter = painterResource(resource = imageRes),
        contentDescription = "meme"
    )
}