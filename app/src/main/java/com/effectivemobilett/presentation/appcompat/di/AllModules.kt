package com.effectivemobilett.presentation.appcompat.di

import com.effectivemobilett.di.dataModule
import com.effectivemobilett.di.domainModule
import com.effectivemobilett.di.netWorkModule

fun allModules() = listOf(
    netWorkModule(),
    dataModule(),
    domainModule(),
    presentationModule()
)
