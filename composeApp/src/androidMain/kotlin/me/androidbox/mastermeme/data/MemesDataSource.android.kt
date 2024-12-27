package me.androidbox.mastermeme.data

import android.content.ContentUris
import android.content.Context
import android.os.Environment
import android.provider.MediaStore

actual class MemesDataSourceImpl(private val context: Context) : MemesDataSource {
    actual override fun getCreatedMemes(): List<String> {
        val fileUriList = mutableListOf<String>()

        // Columns you want to retrieve
        val projection = arrayOf(
            MediaStore.Images.Media._ID, // File ID
        )

        // Query for files in the specified relative path
        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
        val selectionArgs = arrayOf("${Environment.DIRECTORY_PICTURES}/masterMeme%")

        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        context.contentResolver.query(uri, projection, selection, selectionArgs, null)?.use { cursor ->
            val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                val fileUri = ContentUris.withAppendedId(uri, id)
                fileUriList.add(fileUri.toString())
            }
        }

        return fileUriList
    }
}