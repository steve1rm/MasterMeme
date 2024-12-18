package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.mastermeme.presentation.TextMemeData

@Composable
fun EditorMenu(
    modifier: Modifier = Modifier,
    textMemeData: TextMemeData,
    onClose: () -> Unit,
    onSliderPositionChange: (Float) -> Unit,
    onCheck: () -> Unit
) {
    Box(
        modifier = modifier.padding(top = 12.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Icon
            IconButton(
                modifier = Modifier.size(44.dp),
                onClick = { onClose.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color(0xFFCCC2DC), // Light purple tint
                    modifier = Modifier.size(24.dp)
                )
            }

            // Left Label
            Text(
                text = "Aa",
                style = TextStyle(fontSize = 12.sp, color = Color.White),
                modifier = Modifier.padding(start = 12.dp)
            )

            ManualSlider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                currentValue = textMemeData.fontSize.value.value,
                onSliderPositionChange = { onSliderPositionChange.invoke(it) },
                valueRange = 12f..40f
            )

            // Right Label
            Text(
                text = "Aa",
                style = TextStyle(fontSize = 24.sp, color = Color.White),
                modifier = Modifier.padding(end = 12.dp)
            )

            // Check Icon
            IconButton(
                modifier = Modifier.size(44.dp),
                onClick = { onCheck.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = Color(0xFFCCC2DC), // Light purple tint
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun ManualSlider(
    modifier: Modifier = Modifier,
    currentValue: Float,
    onSliderPositionChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>
) {
    var sliderPosition by remember { mutableStateOf(0f) } // Slider value: 0f to 1f
    val thumbSize = 24.dp // Size of the thumb (circle)
    val trackHeight = 1.dp // Thickness of the slider line
    val minValue = valueRange.start
    val maxValue = valueRange.endInclusive

    LaunchedEffect(Unit) {
        println("$currentValue, $maxValue $minValue")
        sliderPosition = (currentValue - minValue) / (maxValue - minValue)
        println(sliderPosition)
    }

    BoxWithConstraints(
        modifier = modifier
            .padding(horizontal = thumbSize / 2)
            .height(thumbSize * 2),
        contentAlignment = Alignment.CenterStart
    ) {
        val trackWidth = constraints.maxWidth.toFloat()

        // Convert thumb size to pixels
        val density = LocalDensity.current
        val thumbSizePx = with(density) { thumbSize.toPx() }

        // Calculate thumb position
        val thumbOffset = sliderPosition * trackWidth - (thumbSizePx / 2)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(thumbSize * 2),
            contentAlignment = Alignment.CenterStart
        ) {
            // Slider Track (Line)
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(trackHeight)
                    .align(Alignment.Center)
            ) {
                drawRect(
                    color = Color(0xFFCCC2DC),
                    size = Size(size.width, size.height)
                )
            }

            // Custom Thumb (Circle)
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .offset { IntOffset(thumbOffset.toInt(), 0) }
                    .background(Color(0x33CCC2DC), shape = CircleShape)
                    .pointerInput(Unit) {
                        // Detect Drag Gesture
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            sliderPosition =
                                (sliderPosition + dragAmount.x / trackWidth).coerceIn(0f, 1f)
                            val resultValue = minValue + (sliderPosition * (maxValue - minValue))
                            onSliderPositionChange.invoke(resultValue)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color(0xFFCCC2DC), shape = CircleShape)
                )
            }
        }
    }
}