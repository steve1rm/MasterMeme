package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun EditMemeDialog(
    modifier: Modifier = Modifier,
    title: String,
    memeText: String,
    memeTextChanged: (text: String) -> Unit,
    onDismiss: () -> Unit
) {

    var memeTextValue by remember {
        mutableStateOf(memeText)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .background(
                    color = Color(0xFF211F26),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(border = BorderStroke(1.dp, Color(0x0DEADDFF)))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = title,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color(0xFFE6E0E9)
                )
            )

            // TODO ADJUST START LINE
            TextField(
                modifier = modifier.fillMaxWidth(),
                value = memeTextValue,
                singleLine = true,
                onValueChange = { newMemeValue ->
                    memeTextValue = newMemeValue
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                        text = "Cancel",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFFCCC2DC)
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                TextButton(
                    onClick = {
                        memeTextChanged(memeTextValue)
                        onDismiss()
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                        text = "OK",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFFCCC2DC)
                        )
                    )
                }
            }
        }
    }
}
