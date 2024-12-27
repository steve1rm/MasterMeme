package me.androidbox.mastermeme.data

actual class MemesDataSourceImpl : MemesDataSource {
    actual override fun getCreatedMemes(): List<String> {
        return listOf()
    }
}