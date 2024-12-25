package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.mastermeme.MasterMemeScreen
import me.androidbox.mastermeme.presentation.SURFACE_CONTAINER_LOW_COLOR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterMemeAppBar(
    modifier: Modifier = Modifier,
    currentScreen: MasterMemeScreen,
    canNavigateBack: Boolean,
    navigateUpConfirmation: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarColors(
            containerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
            scrolledContainerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 16.dp)
                        .size(44.dp),
                    onClick = {
                        navigateUpConfirmation.invoke()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Large floating action button",
                        tint = Color(0xFFCCC2DC)
                    )
                }
            }
        },
        title = {
            val titleText = when (currentScreen) {
                MasterMemeScreen.HomeScreen -> "Your memes"
                MasterMemeScreen.EditorScreen -> "New meme"
            }
            val titleTextAlign = when (currentScreen) {
                MasterMemeScreen.HomeScreen -> TextAlign.Start
                MasterMemeScreen.EditorScreen -> TextAlign.Center
            }
            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                text = titleText,
                textAlign = titleTextAlign,
                style = TextStyle(color = Color(0xFFE6E0E9), fontSize = 24.sp)
            )
        },
        actions = {
            if (canNavigateBack) {
                Spacer(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                        .size(44.dp)
                )
            }
        }
    )
}