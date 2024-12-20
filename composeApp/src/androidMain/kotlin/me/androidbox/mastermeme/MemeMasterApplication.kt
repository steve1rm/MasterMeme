package me.androidbox.mastermeme

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MemeMasterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin(
            koinConfig = {
                androidContext(this@MemeMasterApplication)
                androidLogger(Level.DEBUG)
            },
            androidSpecificModule
        )
    }
}