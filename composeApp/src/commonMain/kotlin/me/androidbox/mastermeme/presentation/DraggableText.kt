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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.close_text
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt


@Composable
fun DraggableText(
    onClickClose: () -> Unit,
    onDoubleClickText: (text: String) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val textValue by remember {
        mutableStateOf("DOUBLE TAP TO EDIT")
    }

    Box(
        modifier = Modifier.offset {
            IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
        }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {

        Box(modifier = Modifier
            .background(Color.Transparent)
            .border(shape = RoundedCornerShape(8.dp), color = Color.White, width = 1.dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp),) {

            Box(
                modifier = Modifier
            ) {

                Text(
                    modifier = Modifier.pointerInput(Unit) {
                        this.detectTapGestures(
                            onDoubleTap = {
                                onDoubleClickText(textValue)
                            }
                        )
                    },
                    text = textValue,
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = Color.Black,
                        drawStyle = Stroke(
                            miter = 10f,
                            width = 5f,
                            join = StrokeJoin.Round
                        )
                    )
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
                    y = -(8).dp)
                .clickable(onClick = onClickClose),
            tint = Color.Unspecified
        )
    }
}





























