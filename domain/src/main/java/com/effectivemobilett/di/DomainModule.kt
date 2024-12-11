package com.effectivemobilett.di

import com.effectivemobilett.mainscreen.usecases.GetBaseCourseByIdUseCase
import com.effectivemobilett.mainscreen.usecases.GetCourseIdsByCategoryId
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun domainModule() = module {
    factoryOf(::GetCourseIdsByCategoryId)
    factoryOf(::GetBaseCourseByIdUseCase)
}
