package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.androidbox.mastermeme.presentation.TextMemeData

@Composable
fun EditorMenu(
    modifier: Modifier = Modifier,
    textMemeData: TextMemeData?,
    textSizeAction: (TextSizeAction) -> Unit,
    defaultMenuAction: (DefaultMenuAction) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (textMemeData != null) {
            TextSizeMenu(
                modifier = modifier.fillMaxWidth().padding(top = 12.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                data = textMemeData,
                action = textSizeAction
            )
        } else {
            DefaultMenu(
                modifier = Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                action = defaultMenuAction
            )
        }
    }
}
