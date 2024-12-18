package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.impact
import org.jetbrains.compose.resources.Font

@Composable
fun StrokeAndFillText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    strokeColor: Color,
    fillColor: Color,
    strokeWidth: Float
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Stroke text
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = FontFamily(Font(Res.font.impact)),
                color = strokeColor,
                drawStyle = Stroke(width = strokeWidth)
            ),
            softWrap = false
        )
        // Fill text
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = FontFamily(Font(Res.font.impact)),
                color = fillColor
            ),
            softWrap = false
        )
    }
}