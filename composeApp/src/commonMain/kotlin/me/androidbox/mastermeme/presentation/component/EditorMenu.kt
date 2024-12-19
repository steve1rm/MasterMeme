@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.mastermeme.presentation.TextMemeData

@Composable
fun EditorMenu(
    modifier: Modifier = Modifier,
    textMemeData: TextMemeData,
    onClose: (Float) -> Unit,
    onSliderPositionChange: (Float) -> Unit,
    onCheck: () -> Unit
) {
    val startValue = remember { textMemeData.fontSize.value.value }

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
                onClick = {
                    onClose.invoke(startValue)
                }
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

            Slider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                value = textMemeData.fontSize.value.value,
                steps = 14,
                valueRange = 12f..40f,
                onValueChange = { onSliderPositionChange.invoke(it) },
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color(0x33CCC2DC), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(0xFFCCC2DC), shape = CircleShape)
                        )
                    }
                },
                track = {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    ) {
                        drawRect(
                            color = Color(0xFFCCC2DC),
                            size = Size(size.width, size.height)
                        )
                    }
                },
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
