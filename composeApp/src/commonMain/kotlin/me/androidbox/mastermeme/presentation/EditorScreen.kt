package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.p2is_38
import me.androidbox.mastermeme.presentation.component.DefaultMenuAction
import me.androidbox.mastermeme.presentation.component.EditorMenu
import me.androidbox.mastermeme.presentation.component.TextSizeAction
import org.jetbrains.compose.resources.painterResource

const val SURFACE_CONTAINER_LOW_COLOR = 0xFF1D1B20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    modifier: Modifier = Modifier
) {
    var shouldShowDialog by remember {
        mutableStateOf(false)
    }

    val listOfMemeText = remember {
        mutableStateListOf<TextMemeData>()
    }

    var memeIndex by remember {
        mutableIntStateOf(0)
    }

    var isEditMode by remember {
        mutableStateOf(false)
    }

    var sliderPosition by remember { mutableFloatStateOf(12f) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
                    scrolledContainerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp)
                            .size(44.dp),
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.padding(10.dp).size(24.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Large floating action button",
                            tint = Color(0xFFCCC2DC)
                        )
                    }

                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        text = "New meme",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color(0xFFE6E0E9), fontSize = 24.sp)
                    )
                },
                actions = {
                    Spacer(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                            .size(44.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color.Black),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        modifier = Modifier.padding(horizontal = 16.dp).size(380.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(resource = Res.drawable.p2is_38),
                        contentDescription = "meme"
                    )

                    listOfMemeText.forEachIndexed { index, data ->
                        key(data.id) {
                            DraggableText(
                                textMemeData = data,
                                onClickClose = {
                                    println("onclickClose $index ${listOfMemeText[index].text.value} ${listOfMemeText[index].x.value} ${listOfMemeText[index].y.value}")
                                    val textIndex = listOfMemeText.indexOfFirst {
                                        it.id == data.id
                                    }
                                    if (textIndex != -1) {
                                        listOfMemeText.removeAt(textIndex)
                                    }
                                },
                                onSingleClick = { fontSize ->
                                    isEditMode = true
                                    memeIndex = index
                                    sliderPosition = fontSize.value
                                },
                                onDoubleClickText = { text ->
                                    memeIndex = index
                                    shouldShowDialog = true
                                },
                                updateCoordinates = { x, y ->
                                    println("update coordinates index $index X $x Y $y")
                                    val textIndex = listOfMemeText.indexOfFirst {
                                        it.id == data.id
                                    }

                                    if (textIndex != -1) {
                                        listOfMemeText[textIndex].x.value = x
                                        listOfMemeText[textIndex].y.value = y
                                    }
                                }
                            )
                        }
                    }
                }
            }

            EditorMenu(
                modifier = Modifier.fillMaxWidth().height(70.dp).background(Color(0xFF1D1B20)),
                isEditMode = isEditMode,
                textMemeData = listOfMemeText.getOrNull(memeIndex),
                textSizeAction = { action ->
                    when (action) {
                        is TextSizeAction.Close -> {
                            listOfMemeText[memeIndex].fontSize.value = action.startValue.sp
                            isEditMode = false
                        }
                        is TextSizeAction.ValueChange -> {
                            listOfMemeText[memeIndex].fontSize.value = action.value.sp
                            sliderPosition = action.value
                        }
                        TextSizeAction.Save -> {
                            isEditMode = false
                        }
                    }
                },
                defaultMenuAction = { action ->
                    when (action) {
                        DefaultMenuAction.AddMemeText -> {
                            listOfMemeText.add(
                                TextMemeData(
                                    isEditState = false
                                )
                            )
                        }
                        DefaultMenuAction.SaveMeme -> {
                            // TODO UPDATE HERE
                        }
                    }

                }
            )

            if (shouldShowDialog) {
                EditMemeDialog(
                    title = "Text",
                    memeText = listOfMemeText[memeIndex].text.value,
                    onDismiss = {
                        shouldShowDialog = false
                    },
                    memeTextChanged = { newMemeText ->
                        println(newMemeText)
                        listOfMemeText[memeIndex].text.value = newMemeText
                    },
                )
            }
        }
    }
}