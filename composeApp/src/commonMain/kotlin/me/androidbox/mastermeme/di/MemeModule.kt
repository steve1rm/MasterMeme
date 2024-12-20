package me.androidbox.mastermeme.di

import me.androidbox.mastermeme.data.MemeEditorOptions
import me.androidbox.mastermeme.presentation.MemeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val memeModule = module {

    viewModelOf(::MemeViewModel)
}