package me.androidbox.mastermeme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.download
import mastermeme.composeapp.generated.resources.share
import me.androidbox.mastermeme.presentation.EditMemeDialog
import me.androidbox.mastermeme.presentation.component.SaveShareContent
import me.androidbox.mastermeme.presentation.component.SaveShareItem
import org.jetbrains.compose.resources.vectorResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Composable
@Preview()
fun PreviewEditMemeDialog() {
    EditMemeDialog(
        title = "Text",
        onDismiss = {},
        modifier = Modifier,
        memeText = LoremIpsum(3).toString(),
        memeTextChanged = {},
    )
}

@org.jetbrains.compose.ui.tooling.preview.Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Preview(showBackground = true)
@Composable
fun SaveShareContentPreview() {
    SaveShareContent(
        onSaveClicked = {},
        onShareClicked = {}
    )
}

@Preview
@Composable
fun SaveItemPreview() {
    SaveShareItem(
        title = "Save to device",
        description = "Save this item to your collection.",
        icon = { Icon(imageVector = vectorResource(resource = Res.drawable.download), contentDescription = null, tint = Color.Unspecified) },
        onClick = {}
    )
}

@Preview
@Composable
fun ShareItemPreview() {
    SaveShareItem(
        title = "Share the meme",
        description = "Share this item with your friends.",
        icon = { Icon(imageVector = vectorResource(resource = Res.drawable.share), contentDescription = null, tint = Color.Unspecified) },
        onClick = {}
    )
}

