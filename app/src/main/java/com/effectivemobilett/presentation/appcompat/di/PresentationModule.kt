package com.effectivemobilett.presentation.appcompat.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.effectivemobilett.presentation.main.MainViewModel

fun presentationModule() = module {
    factoryOf(::MainViewModel)
}
