package me.androidbox.mastermeme.data

interface MemesDataSource {
    fun getCreatedMemes(): List<String>
}

expect class MemesDataSourceImpl: MemesDataSource {
    override fun getCreatedMemes(): List<String>
}