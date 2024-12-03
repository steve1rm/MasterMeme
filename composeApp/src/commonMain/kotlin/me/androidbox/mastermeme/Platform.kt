package me.androidbox.mastermeme

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform