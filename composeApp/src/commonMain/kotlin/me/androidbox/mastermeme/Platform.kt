package me.androidbox.mastermeme

import androidx.compose.ui.graphics.ImageBitmap
import me.androidbox.mastermeme.data.MemeEditorOptions

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class MemeEditorOptionsImp : MemeEditorOptions {
    override suspend fun saveMeme(imageBitmap: ImageBitmap, fileName: String): String?
    override fun shareMeme()
}