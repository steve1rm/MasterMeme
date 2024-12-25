@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.androidbox.mastermeme.presentation.component.SaveShareContent

@Composable
fun SaveShareMemeBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSaveClicked: () -> Unit,
    onShareClicked: () -> Unit,
    sheetState: SheetState
) {

    ModalBottomSheet(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.systemBars),
        sheetState = sheetState,
        containerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
        onDismissRequest = onDismiss,
        scrimColor = Color(SCRIM_COLOR),
    ) {
        SaveShareContent(
            modifier = Modifier.padding(horizontal = 16.dp),
            onSaveClicked = onSaveClicked,
            onShareClicked = onShareClicked
        )
    }
}