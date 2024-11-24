package com.effectivemobilett.mainscreen.usecases

import com.effectivemobilett.mainscreen.model.BaseCourseModel
import com.effectivemobilett.mainscreen.repository.MainScreenRepository

class GetCoursesUseCase(private val courseRepository: MainScreenRepository) {

    suspend operator fun invoke(
        courseSection: String? = null,
        sortKey: Int? = null,
    ): List<BaseCourseModel> {
        return courseRepository.getSortedCoursesByName(courseSection, sortKey)
    }
}