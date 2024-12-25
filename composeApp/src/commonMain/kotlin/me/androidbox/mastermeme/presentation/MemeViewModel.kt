package me.androidbox.mastermeme.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.androidbox.mastermeme.data.MemeEditorOptions
import org.jetbrains.compose.resources.DrawableResource

class MemeViewModel(
    private val memeEditorOptions: MemeEditorOptions
): ViewModel() {

    var selectedMemeState = mutableStateOf<DrawableResource?>(null)
        private set

    fun selectMeme(selectedMeme: DrawableResource) {
        selectedMemeState.value = selectedMeme
    }

    var memeState = mutableStateOf<String?>(null)
        private set

    private val _onSuccessSaveMeme = Channel<String>(Channel.BUFFERED)
    val onSuccessSaveMemeFlow = _onSuccessSaveMeme.receiveAsFlow()

    fun saveMeme(imageBitmap: ImageBitmap, fileName: String) {
        viewModelScope.launch {
            try {
                memeState.value = memeEditorOptions.saveMeme(imageBitmap, fileName)
                _onSuccessSaveMeme.send("Finished Saved Meme")
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