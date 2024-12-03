package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.meme_man
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemeScreen(
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            confirmStateChange = { bottomSheetValue ->
                when(bottomSheetValue) {
                    BottomSheetValue.Collapsed -> {
                        println("Collapsed")
                    }
                    BottomSheetValue.Expanded -> {
                        println("Expanded")
                    }
                }

                true
            }
        )
    )

    BottomSheetScaffold(
        modifier = modifier,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetState,
        sheetContent = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
            Text(text = "This is the bottom sheet content", fontSize = 32.sp)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.meme_man),
                    contentDescription = "meme man"
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.bottomSheetState.expand()
                    }
                },
                shape = CircleShape,
            ) {
                Icon(Icons.Filled.Add, "Large floating action button")
            }
        },
        floatingActionButtonPosition = FabPosition.End)
}