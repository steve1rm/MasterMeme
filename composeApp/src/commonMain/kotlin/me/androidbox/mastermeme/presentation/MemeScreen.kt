package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.androidbox.mastermeme.presentation.component.EmptyMemeView
import me.androidbox.mastermeme.presentation.component.MemeListView
import me.androidbox.mastermeme.presentation.component.TemplateMemeBottomSheet

const val ON_PRIMARY_FIXED_COLOR = 0xFF21005D
const val SURFACE_CONTAINER_LOWEST_COLOR = 0xFF0F0D13
const val OUTLINE_COLOR = 0xFF79747E
const val LIGHT_GRAY_C0LOR = 0xFFE6E0E9
const val SCRIM_COLOR = 0xB20F0D13

@Composable
fun MemeScreen(
    modifier: Modifier = Modifier,
    viewModel: MemeViewModel,
    onClickMeme: (memeRes: Any) -> Unit
) {

    var showBottomSheet by remember { mutableStateOf(false) }

    val listImages = viewModel.createdMemesState.toList()

    LaunchedEffect(Unit) {
        viewModel.getCreatedMemes()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        if (listImages.isEmpty()) {
            EmptyMemeView()
        } else {
            MemeListView(
                data = listImages,
                onClickMeme = onClickMeme
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(bottom = 32.dp, end = 16.dp),
            containerColor = Color(0xFFEADDFF),
//                Brush.horizontalGradient(
//                    colorStops = arrayOf(
//                        0.0F to Color(0xFFEADDFF),
//                        1.0F to Color(0xFFD0BCFE)
//                    )
//                ),
            contentColor = Color(ON_PRIMARY_FIXED_COLOR),
            onClick = {
                showBottomSheet = true
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add Meme"
            )
        }

        if (showBottomSheet) {
            TemplateMemeBottomSheet(
                onDismiss = { showBottomSheet = false },
                onClickMeme = onClickMeme
            )
        }
    }
}