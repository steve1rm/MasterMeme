package me.androidbox.mastermeme.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import me.androidbox.mastermeme.data.MemeEditorOptions

class MemeViewModel(
    private val memeEditorOptions: MemeEditorOptions
): ViewModel() {

    var memeState = mutableStateOf<String?>(null)
        private set

    fun saveMeme(imageBitmap: ImageBitmap, fileName: String) {
        viewModelScope.launch {
            try {
                memeState.value = memeEditorOptions.saveMeme(imageBitmap, fileName)
                println("Finished meme ${memeState.value}")
            }
            catch (exception: Exception) {
                ensureActive()

                exception.printStackTrace()
            }
        }
    }

    fun shareMeme() {
        memeEditorOptions.shareMeme(memeState.value)
    }
}