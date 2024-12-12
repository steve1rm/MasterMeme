package me.androidbox.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        description = "Double Tap to Edit",
        modifier = Modifier,
    )
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

