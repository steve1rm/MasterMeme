package me.androidbox.mastermeme.presentation

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.mastermeme.data.MemeEditorOptions

class MemeViewModel(
    private val memeEditorOptions: MemeEditorOptions
): ViewModel() {

    fun saveMeme(imageBitmap: ImageBitmap, fileName: String) {
        viewModelScope.launch {
            memeEditorOptions.saveMeme(imageBitmap, fileName)
        }
    }
}