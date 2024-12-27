package me.androidbox.mastermeme

import me.androidbox.mastermeme.data.MemeEditorOptions
import me.androidbox.mastermeme.data.MemesDataSource
import me.androidbox.mastermeme.data.MemesDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidSpecificModule = module {

    factory<MemeEditorOptions> {
        MemeEditorOptionsImp(androidApplication())
    }

    factory<MemesDataSource> {
        MemesDataSourceImpl(androidApplication())
    }
}
