package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import mastermeme.composeapp.generated.resources.ajtl_46
import mastermeme.composeapp.generated.resources.eyvu_45
import mastermeme.composeapp.generated.resources.left_exit_12_off_ramp_3
import mastermeme.composeapp.generated.resources.meme_man
import mastermeme.composeapp.generated.resources.p2is_38
import mastermeme.composeapp.generated.resources.rcrc1_39
import mastermeme.composeapp.generated.resources.t8r9a_26
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemeScreen(
    modifier: Modifier = Modifier,
    onClickMemem: (memeRes: DrawableResource) -> Unit
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

    val state = rememberLazyGridState()
    val listOfMeme = listOf(
        MemeImages(
            Res.drawable.ajtl_46),
        MemeImages(
            Res.drawable.left_exit_12_off_ramp_3),
        MemeImages(
            Res.drawable.p2is_38),
        MemeImages(
            Res.drawable.rcrc1_39),
        MemeImages(
            Res.drawable.t8r9a_26),
        MemeImages(
            Res.drawable.ajtl_46),
        MemeImages(
            Res.drawable.left_exit_12_off_ramp_3),
        MemeImages(
            Res.drawable.p2is_38),
        MemeImages(
            Res.drawable.rcrc1_39),
        MemeImages(
            Res.drawable.t8r9a_26),
        MemeImages(
            Res.drawable.ajtl_46),
        MemeImages(
            Res.drawable.left_exit_12_off_ramp_3),
        MemeImages(
            Res.drawable.p2is_38),
        MemeImages(
            Res.drawable.rcrc1_39),
        MemeImages(
            Res.drawable.t8r9a_26),
        MemeImages(
            Res.drawable.ajtl_46),
        MemeImages(
            Res.drawable.left_exit_12_off_ramp_3),
        MemeImages(
            Res.drawable.p2is_38),
        MemeImages(
            Res.drawable.rcrc1_39),
        MemeImages(
            Res.drawable.t8r9a_26))

    BottomSheetScaffold(
        modifier = modifier,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetState,
        sheetContent = {
            Text(text = "Choose your template", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Choose your template for your next meme masterspace", fontSize = 14.sp)

            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(count = 2),
                state = state,
                content = {
                    items(
                        count = listOfMeme.count(),
                        key = { index ->
                            index
                        },
                        itemContent = { index ->
                            MemeItem(
                                listOfMeme[index].imageRes,
                                onClickMeme = { resMeme ->

                                }
                            )
                        }
                    )
                })
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