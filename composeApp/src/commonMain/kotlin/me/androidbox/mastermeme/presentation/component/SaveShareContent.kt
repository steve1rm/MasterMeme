package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.download
import mastermeme.composeapp.generated.resources.share
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SaveShareContent(
    modifier: Modifier = Modifier,
    onSaveClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        SaveShareItem(
            title = "Save to device",
            description = "Save created meme on the Files of your device",
            icon = {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.download),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClick = onSaveClicked
        )

        SaveShareItem(
            title = "Share the meme",
            description = "Share your meme or open it in another App",
            icon = {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.share),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClick = onShareClicked
        )
    }
}


