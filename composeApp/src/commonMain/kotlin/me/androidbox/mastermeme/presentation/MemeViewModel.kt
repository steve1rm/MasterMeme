package me.androidbox.mastermeme.presentation

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import me.androidbox.mastermeme.data.MemeEditorOptions

class MemeViewModel(
    private val memeEditorOptions: MemeEditorOptions
): ViewModel() {

    suspend fun saveMeme(imageBitmap: ImageBitmap, fileName: String): String? {
        var uriResult: String? = null

        try {
             val result = viewModelScope.async {
                val pathResult = memeEditorOptions.saveMeme(imageBitmap, fileName)

                pathResult
            }
            uriResult = result.await()
        }
        catch (exception: Exception) {
            if(exception is CancellationException) {
                throw CancellationException(exception.message)
            }
            exception.printStackTrace()
        }

        return uriResult
    }
}