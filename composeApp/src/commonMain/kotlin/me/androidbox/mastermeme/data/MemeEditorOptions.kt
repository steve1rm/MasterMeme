package me.androidbox.mastermeme.data

import androidx.compose.ui.graphics.ImageBitmap

interface MemeEditorOptions {
    suspend fun saveMeme(bitmap: ImageBitmap, fileName: String)
    suspend fun openMeme()
}
