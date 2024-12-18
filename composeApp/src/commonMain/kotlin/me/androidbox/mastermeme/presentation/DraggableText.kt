package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.close_text
import me.androidbox.mastermeme.presentation.component.StrokeAndFillText
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt


@Composable
fun DraggableText(
    textMemeData: TextMemeData,
    updateCoordinates: (x: Float, y: Float) -> Unit,
    onClickClose: () -> Unit,
    onDoubleClickText: (text: String) -> Unit,
    onSingleClick: (fontSize: TextUnit) -> Unit
) {

    Box(
        modifier = Modifier.offset {
            IntOffset(textMemeData.x.value.roundToInt(), textMemeData.y.value.roundToInt())
        }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    updateCoordinates(
                        textMemeData.x.value + dragAmount.x,
                        textMemeData.y.value + dragAmount.y
                    )
                }
            }
    ) {

        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .border(shape = RoundedCornerShape(4.dp), color = Color.White, width = 1.dp)
                .padding(vertical = 14.dp, horizontal = 12.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
                StrokeAndFillText(
                    modifier = Modifier.pointerInput(Unit) {
                        this.detectTapGestures(
                            onDoubleTap = {
                                onDoubleClickText(textMemeData.text.value)
                            },
                            onTap = {
                                onSingleClick(textMemeData.fontSize.value)
                            }
                        )
                    },
                    text = textMemeData.text.value,
                    strokeColor = Color.Black,
                    fillColor = Color.White,
                    fontSize = textMemeData.fontSize.value,
                    strokeWidth = 2f
                )
            }
        }

        Icon(
            painter = painterResource(resource = Res.drawable.close_text),
            contentDescription = "close",
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.TopEnd)
                .offset(
                    x = 8.dp,
                    y = -(8).dp
                )
                .clickable(onClick = onClickClose),
            tint = Color.Unspecified
        )
    }
}





























