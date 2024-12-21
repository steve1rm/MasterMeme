package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SaveShareItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable(
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        icon()

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFCCC2DC))

            Text(text = description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF79747E))
        }
    }
}

@Preview
@Composable
fun SaveItemPreview() {
    SaveShareItem(
        title = "Save",
        description = "Save this item to your collection.",
        icon = {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
        },
        onClick = {}
    )
}
@Preview
@Composable
fun ShareItemPreview() {
    SaveShareItem(
        title = "Share",
        description = "Share this item with your friends.",
        icon = {
            Icon(Icons.Filled.Share, contentDescription = null)
        },
        onClick = {}
    )
}