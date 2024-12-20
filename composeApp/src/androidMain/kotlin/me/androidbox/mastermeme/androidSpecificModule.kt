package me.androidbox.mastermeme

import me.androidbox.mastermeme.data.MemeEditorOptions
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidSpecificModule = module {

    factory<MemeEditorOptions> {
        MemeEditorOptionsImp(androidApplication())
    }
}
