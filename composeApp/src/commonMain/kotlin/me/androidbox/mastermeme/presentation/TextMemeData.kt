package me.androidbox.mastermeme.presentation

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf

data class TextMemeData(
    val text: String = "DOUBLE TAP TO EDIT",
    val x: MutableFloatState = mutableFloatStateOf(0.0f),
    val y: MutableFloatState = mutableFloatStateOf(0.0f),
    val isEditState: Boolean
)

