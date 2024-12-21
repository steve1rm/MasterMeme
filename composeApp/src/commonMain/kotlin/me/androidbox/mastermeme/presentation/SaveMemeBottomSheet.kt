@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SaveMemeBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSaveClicked: () -> Unit,
    onShareClicked: () -> Unit,
    content: @Composable (onSave: () -> Unit, onShare: () -> Unit) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    ModalBottomSheet(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.systemBars),
        sheetState = sheetState,
        contentColor = Color(SURFACE_CONTAINER_LOW_COLOR),
        onDismissRequest = onDismiss,
        scrimColor = Color(SCRIM_COLOR),
    ) {

        content(
             {
                onSaveClicked()
            },
            {
                onShareClicked
            })
    }
}