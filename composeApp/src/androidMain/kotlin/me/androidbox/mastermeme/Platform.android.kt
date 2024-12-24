package me.androidbox.mastermeme

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import me.androidbox.mastermeme.data.MemeEditorOptions
import kotlin.coroutines.coroutineContext

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual class MemeEditorOptionsImp(private val context: Context) : MemeEditorOptions {

    actual override suspend fun saveMeme(imageBitmap: ImageBitmap, fileName: String): String? {
        val contentValues = ContentValues().apply {
            this.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            this.put(MediaStore.Images.Media.MIME_TYPE, "image/webp")
            this.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/masterMeme")
        }

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if(uri != null) {
            try {
                val uri = withContext(Dispatchers.IO) {
                    context.contentResolver.openOutputStream(uri).use { outputStream ->
                        if(outputStream != null) {
                            val bitmap = imageBitmap.asAndroidBitmap()
                            bitmap.compress(Bitmap.CompressFormat.WEBP, 100, outputStream)

                            println("MEME outputstream completed")
                        }
                    }
                    println("MEME success $uri")
                    uri
                }

                return uri.path
            }
            catch (exception: Exception) {
                coroutineContext.ensureActive()

                exception.printStackTrace()
            }
        }

        println("MEME failed ${uri?.path}")
        return uri?.path
    }

    actual override fun shareMeme() {
        println("ShareMeme - android")

        val sendIntent = Intent().apply {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.action = Intent.ACTION_SEND
            this.putExtra(Intent.EXTRA_TEXT, "This is the meme path")
            this.type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share Meme").apply {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(shareIntent)
    }
}
