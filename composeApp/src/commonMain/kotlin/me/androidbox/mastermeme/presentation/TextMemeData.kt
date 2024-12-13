package me.androidbox.mastermeme.presentation

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf

data class TextMemeData(
    val text: MutableState<String> = mutableStateOf("DOUBLE TAP TO EDIT"),
    val x: MutableFloatState = mutableFloatStateOf(0.0f),
    val y: MutableFloatState = mutableFloatStateOf(0.0f),
    val isEditState: Boolean
)

