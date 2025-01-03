package com.effectivemobilett.presentation.main

import com.effectivemobilett.mainscreen.model.BaseCourseModel

data class CoursesState(
    val courses: List<BaseCourseModel>? = null,
    val courseItem: BaseCourseModel? = null,
    val searchQuery: String? = null,
    val sortKey: Int = 0,
    val loading: Boolean = false,
    val isSorted: Boolean = false,
    val isReversed: Boolean = false
)
