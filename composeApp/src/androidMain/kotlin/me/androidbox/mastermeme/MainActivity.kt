package me.androidbox.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import me.androidbox.mastermeme.presentation.EditMemeDialog

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

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

