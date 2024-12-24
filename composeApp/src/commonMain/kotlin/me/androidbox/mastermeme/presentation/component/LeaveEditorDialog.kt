package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LeaveEditorDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onLeave: () -> Unit,
) {

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
                .padding(top = 24.dp, bottom =  16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Leave editor?",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color(0xFFE6E0E9)
                )
            )

            Text(
                text = "You will lose your precious meme. If you're fine with that, press ‘Leave’.",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFFE6E0E9)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                    onClick = {
                        onDismiss.invoke()
                    }
                ) {
                    Text(
                        text = "Cancel",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFFCCC2DC)
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                TextButton(
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                    onClick = {
                        onLeave.invoke()
                    }
                ) {
                    Text(
                        text = "Leave",
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