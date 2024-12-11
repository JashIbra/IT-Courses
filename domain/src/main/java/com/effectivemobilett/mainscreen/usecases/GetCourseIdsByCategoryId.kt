package com.effectivemobilett.mainscreen.usecases

import com.effectivemobilett.mainscreen.repository.MainScreenRepository

class GetCourseIdsByCategoryId(private val mainScreenRepository: MainScreenRepository) {
    suspend operator fun invoke(categoryId: Long): List<Long> {
        return mainScreenRepository.getCourseIdsByCategoryId(categoryId)
    }
}
