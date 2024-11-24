package com.effectivemobilett.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.effectivemobilett.mainscreen.usecases.GetCoursesUseCase

fun domainModule() = module {
    factoryOf(::GetCoursesUseCase)
}
