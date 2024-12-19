package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.mastermeme.presentation.LIGHT_GRAY_C0LOR
import me.androidbox.mastermeme.presentation.SCRIM_COLOR
import me.androidbox.mastermeme.presentation.SURFACE_CONTAINER_LOW_COLOR
import org.jetbrains.compose.resources.DrawableResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateMemeBottomSheet(
    modifier: Modifier = Modifier,
    data: List<DrawableResource>,
    onDismiss: () -> Unit,
    onClickMeme: (memeRes: DrawableResource) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    ModalBottomSheet(
        modifier = modifier
            .fillMaxHeight()
            .windowInsetsPadding(WindowInsets.systemBars),
        sheetState = sheetState,
        containerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
        onDismissRequest = { onDismiss.invoke() },
        scrimColor = Color(SCRIM_COLOR)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Choose template",
            style = TextStyle.Default.copy(
                color = Color(LIGHT_GRAY_C0LOR),
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Choose template for your next meme masterpiece",
            style = TextStyle.Default.copy(
                color = Color(LIGHT_GRAY_C0LOR),
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        MemeListView(
            showTopGradient = false,
            data = data,
            onClickMeme = onClickMeme
        )
    }
}