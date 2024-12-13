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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.p2is_38
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    modifier: Modifier = Modifier
) {

    var addMemeText by remember {
        mutableStateOf("DOUBLE TAP TO EDIT")
    }

    var shouldShowDialog by remember {
        mutableStateOf(false)
    }

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
                Box(modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.Center) {

                    Image(
                        modifier = Modifier.padding(horizontal = 16.dp).size(380.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(resource = Res.drawable.p2is_38),
                        contentDescription = "meme"
                    )

                    DraggableText(
                        memeText = addMemeText,
                        onClickClose = {
                            println("Close")
                        },
                        onDoubleClickText = { text ->
                            println(text)
                            shouldShowDialog = true
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().height(70.dp).background(Color.LightGray)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.width(111.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Text("1")
                    Text("2")
                }
                Text(modifier = Modifier.width(111.dp).clickable(
                    onClick = {

                    }
                ), text = "Add Text", textAlign = TextAlign.Center)
                Text(modifier = Modifier.width(111.dp), text = "Save Meme",textAlign = TextAlign.Center)
            }

            if(shouldShowDialog) {
                EditMemeDialog(
                    title = "Text",
                    memeText = addMemeText,
                    onDismiss = {
                        shouldShowDialog = false
                    },
                    memeTextChanged = { newMemeText ->
                        println(newMemeText)
                        addMemeText = newMemeText
                    },
                )
            }
        }
    }
}