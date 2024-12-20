package me.androidbox.mastermeme

import me.androidbox.mastermeme.di.memeModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initializeKoin(koinConfig: KoinAppDeclaration? = null, vararg platformSpecificModules: Module = emptyArray()) {

    startKoin {
        koinConfig?.invoke(this@startKoin)

        this.modules(
            memeModule,
            *platformSpecificModules
        )
    }
}