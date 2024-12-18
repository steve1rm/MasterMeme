package me.androidbox.mastermeme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import me.androidbox.mastermeme.presentation.component.EditorMenu
import org.jetbrains.compose.resources.painterResource

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
                modifier = Modifier.background(Color.DarkGray),
                colors = TopAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.LightGray,
                    actionIconContentColor = Color.LightGray,
                    navigationIconContentColor = Color.LightGray,
                    scrolledContainerColor = Color.LightGray
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Large floating action button",
                        tint = Color.LightGray
                    )
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "New meme",
                        textAlign = TextAlign.Center
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

            Row(
                modifier = Modifier.fillMaxWidth().height(70.dp).background(Color(0xFF1D1B20)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isEditMode) {
                    EditorMenu(
                        modifier = Modifier.fillMaxWidth(),
                        onClose = { isEditMode = false },
                        onSliderPositionChange = { value ->
                            listOfMemeText[memeIndex].fontSize.value = value.sp
                            sliderPosition = value
                        },
                        onCheck = { isEditMode = false }
                    )
                } else {
                    Row(
                        modifier = Modifier.width(111.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Text("1")
                        Text("2")
                    }

                    Text(
                        modifier = Modifier.width(111.dp).clickable(
                            onClick = {
                                listOfMemeText.add(
                                    TextMemeData(
                                        isEditState = false
                                    )
                                )
                            }
                        ),
                        text = "Add Text",
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 18.sp, color = Color.White)
                    )

                    Text(
                        modifier = Modifier
                            .width(111.dp),
                        text = "Save Meme",
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 18.sp, color = Color.White)
                    )
                }
            }

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