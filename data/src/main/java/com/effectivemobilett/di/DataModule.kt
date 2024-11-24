package com.effectivemobilett.di

import com.effectivemobilett.api.CoursesApi
import com.effectivemobilett.mainscreen.repository.MainScreenRepositoryImpl
import com.effectivemobilett.mainscreen.repository.MainScreenRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun dataModule() = module {

    single { getRetrofit().create(CoursesApi::class.java) }
    single<MainScreenRepository> { MainScreenRepositoryImpl(coursesApi = get()) }
    singleOf(::MainScreenRepositoryImpl) bind MainScreenRepository::class
}
