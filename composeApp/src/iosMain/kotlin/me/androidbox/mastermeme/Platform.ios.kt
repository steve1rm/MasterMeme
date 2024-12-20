package me.androidbox.mastermeme

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import me.androidbox.mastermeme.data.MemeEditorOptions
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual class MemeEditorOptionsImp : MemeEditorOptions {
    actual override suspend fun saveMeme(imageBitmap: ImageBitmap, fileName: String) {
        val bitmap = imageBitmap.asSkiaBitmap()
        TODO("Not yet implemented")
    }

    actual override suspend fun openMeme() {
        TODO("Not yet implemented")
    }
}