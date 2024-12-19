package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class DefaultMenuAction {
    data object AddMemeText : DefaultMenuAction()
    data object SaveMeme : DefaultMenuAction()
}

@Composable
fun DefaultMenu(
    modifier: Modifier = Modifier,
    action: (DefaultMenuAction) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        TextButton(
            modifier = Modifier
                .height(40.dp)
                .align(Alignment.Center)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x80EADDFF),
                                Color(0x80D0BCFF)
                            )
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
            onClick = {
                action.invoke(DefaultMenuAction.AddMemeText)
            }
        ) {
            Text(
                text = "Add text",
                style = TextStyle(
                    fontSize = 14.sp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFEADDFF),
                            Color(0xFFD0BCFF)
                        )
                    )
                ),
            )
        }
        TextButton(
            modifier = Modifier
                .height(40.dp)
                .align(Alignment.CenterEnd)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFEADDFF),
                            Color(0xFFD0BCFF)
                        )
                    )
                ),
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
            onClick = {
                action.invoke(DefaultMenuAction.SaveMeme)
            }
        ) {
            Text(
                text = "Save meme",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF21005D)
                )
            )
        }
    }
}