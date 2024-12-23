package me.androidbox.mastermeme.presentation

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlin.random.Random

data class TextMemeData(
    val id: Int = Random.nextInt(),
    val text: MutableState<String> = mutableStateOf("DOUBLE TAP TO EDIT"),
    val x: MutableFloatState = mutableFloatStateOf(0.0f),
    val y: MutableFloatState = mutableFloatStateOf(0.0f),
    var isEditState: MutableState<Boolean> = mutableStateOf(true),
    var fontSize: MutableState<TextUnit> = mutableStateOf(28.sp)
)

