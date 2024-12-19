package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemeListView(
    modifier: Modifier = Modifier,
    showTopGradient: Boolean = true,
    data: List<DrawableResource>,
    onClickMeme: (memeRes: DrawableResource) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 22.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(data.size) { index ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            onClickMeme.invoke(data[index])
                        },
                    painter = painterResource(data[index]), contentDescription = "Images",
                    contentScale = ContentScale.Crop
                )
            }
        }
        if (showTopGradient) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0F to Color(0xFF141218),
                                1.0F to Color(0x00141218)
                            )
                        ),
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0F to Color(0x00141218),
                            1.0F to Color(0xFF141218)
                        )
                    ),
                )
        )
    }
}