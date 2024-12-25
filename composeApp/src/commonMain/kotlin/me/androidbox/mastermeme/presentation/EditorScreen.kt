package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import me.androidbox.mastermeme.presentation.component.DefaultMenuAction
import me.androidbox.mastermeme.presentation.component.EditorMenu
import me.androidbox.mastermeme.presentation.component.LeaveEditorDialog
import me.androidbox.mastermeme.presentation.component.TextSizeAction
import org.jetbrains.compose.resources.painterResource

const val SURFACE_CONTAINER_LOW_COLOR = 0xFF1D1B20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    memeViewModel: MemeViewModel,
    shouldShowLeaveDialog: Boolean,
    onDismissLeaveDialog: () -> Unit,
    onLeaveEditorScreen: () -> Unit
) {
    var shouldShowTextEditDialog by remember {
        mutableStateOf(false)
    }

    var shouldShowSaveBottomSheet by remember {
        mutableStateOf(false)
    }

    val listOfMemeText = remember {
        mutableStateListOf<TextMemeData>()
    }

    var memeIndex by remember {
        mutableIntStateOf(-1)
    }

    LaunchedEffect(memeIndex) {
        if (listOfMemeText.size == 0) return@LaunchedEffect
        for (index in 0 until listOfMemeText.size) {
            listOfMemeText[index].isEditState.value = index == memeIndex
        }
    }

    var sliderPosition by remember { mutableFloatStateOf(12f) }

    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    LaunchedEffect(key1 = memeViewModel.memeState.value) {
        if (!memeViewModel.memeState.value.isNullOrBlank()) {
            println("saved meme path ${memeViewModel.memeState.value}")
            shouldShowSaveBottomSheet = false
        }
    }

    LaunchedEffect(Unit) {
        memeViewModel.onSuccessSaveMemeFlow.collect { _ ->
            navController.popBackStack()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .drawWithContent {
                        /** Call record to capture the content in the graphics layer */
                        graphicsLayer.record {
                            /** Draw the contents of the composable into the graphics layer */
                            this@drawWithContent.drawContent()
                        }
                        /** Draw the graphics layer on the visible canvas */
                        this.drawLayer(graphicsLayer)
                    },
                contentAlignment = Alignment.Center
            ) {

                memeViewModel.selectedMemeState.value?.let {
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(380.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    memeIndex = -1
                                }
                            ),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(resource = it),
                        contentDescription = "meme"
                    )
                }

                listOfMemeText.forEach { data ->
                    key(data.id) {
                        DraggableText(
                            textMemeData = data,
                            onClickClose = {
                                val currentIndex = listOfMemeText.indexOfFirst {
                                    it.id == data.id
                                }
                                if (currentIndex != -1) {
                                    listOfMemeText.removeAt(currentIndex)
                                }
                            },
                            onSingleClick = { fontSize ->
                                val currentIndex = listOfMemeText.indexOfFirst {
                                    it.id == data.id
                                }
                                memeIndex = currentIndex
                                listOfMemeText[currentIndex].isEditState.value = true

                                sliderPosition = fontSize.value
                            },
                            onDoubleClickText = {
                                val currentIndex = listOfMemeText.indexOfFirst {
                                    it.id == data.id
                                }
                                memeIndex = currentIndex

                                shouldShowTextEditDialog = true
                            },
                            updateCoordinates = { x, y ->
                                val currentIndex = listOfMemeText.indexOfFirst {
                                    it.id == data.id
                                }
                                if (currentIndex != -1) {
                                    listOfMemeText[currentIndex].x.value = x
                                    listOfMemeText[currentIndex].y.value = y
                                }
                            }
                        )
                    }
                }
            }
        }

        EditorMenu(
            modifier = Modifier.fillMaxWidth().height(70.dp).background(Color(0xFF1D1B20)),
            textMemeData = listOfMemeText.getOrNull(memeIndex),
            textSizeAction = { action ->
                when (action) {
                    is TextSizeAction.Close -> {
                        listOfMemeText[memeIndex].fontSize.value = action.startValue.sp
                        memeIndex = -1
                    }

                    is TextSizeAction.ValueChange -> {
                        listOfMemeText[memeIndex].fontSize.value = action.value.sp
                        sliderPosition = action.value
                    }

                    TextSizeAction.Save -> {
                        memeIndex = -1
                    }
                }
            },
            defaultMenuAction = { action ->
                when (action) {
                    DefaultMenuAction.AddMemeText -> {
                        listOfMemeText.add(TextMemeData())
                        memeIndex = listOfMemeText.size - 1
                    }

                    DefaultMenuAction.SaveMeme -> {
                        shouldShowSaveBottomSheet = true
                        coroutineScope.launch {
                        }
                    }
                }
            }
        )

        if (shouldShowTextEditDialog) {
            EditMemeDialog(
                title = "Text",
                memeText = listOfMemeText[memeIndex].text.value,
                onDismiss = {
                    shouldShowTextEditDialog = false
                },
                memeTextChanged = { newMemeText ->
                    listOfMemeText[memeIndex].text.value = newMemeText
                },
            )
        }

        if (shouldShowLeaveDialog) {
            LeaveEditorDialog(
                onDismiss = {
                    onDismissLeaveDialog.invoke()
                },
                onLeave = {
                    onLeaveEditorScreen.invoke()
                }
            )
        }

        if (shouldShowSaveBottomSheet) {
            SaveShareMemeBottomSheet(
                sheetState = sheetState,
                onDismiss = {
                    shouldShowSaveBottomSheet = false
                },
                onSaveClicked = {
                    coroutineScope.launch {
                        val imageBitmap = graphicsLayer.toImageBitmap()
                        memeViewModel.saveMeme(
                            imageBitmap,
                            "MasterMeme - ${Clock.System.now()}"
                        )
                    }
                },
                onShareClicked = {
                    memeViewModel.shareMeme()
                }
            )
        }
    }
}