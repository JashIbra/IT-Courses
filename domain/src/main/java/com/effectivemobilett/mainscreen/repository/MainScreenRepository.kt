package com.effectivemobilett.mainscreen.repository

import com.effectivemobilett.mainscreen.model.BaseCourseModel

interface MainScreenRepository {

    suspend fun getSortedCoursesByName(
        courseSection: String? = null,
        sortKey: Int? = null,
    ): List<BaseCourseModel>
}
