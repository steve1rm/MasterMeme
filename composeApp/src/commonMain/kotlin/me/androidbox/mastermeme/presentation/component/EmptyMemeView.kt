package me.androidbox.mastermeme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.meme_man
import me.androidbox.mastermeme.presentation.OUTLINE_COLOR
import org.jetbrains.compose.resources.vectorResource

@Composable
fun EmptyMemeView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = vectorResource(Res.drawable.meme_man),
            contentDescription = "Illustration Empty Meme"
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Tap + button to create your first meme",
            style = TextStyle.Default.copy(color = Color(OUTLINE_COLOR)),
            textAlign = TextAlign.Center
        )
    }
}